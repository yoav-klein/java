package org.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.tracing.handler.DefaultTracingObservationHandler;
import io.micrometer.tracing.handler.PropagatingReceiverTracingObservationHandler;
import io.micrometer.tracing.handler.PropagatingSenderTracingObservationHandler;
import io.micrometer.tracing.otel.bridge.OtelCurrentTraceContext;
import io.micrometer.tracing.otel.bridge.OtelPropagator;
import io.micrometer.tracing.otel.bridge.OtelTracer;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;


@Configuration
@ConfigurationProperties(prefix = "observability.tracing")
@ConditionalOnProperty(prefix = "observability.tracing", name = "enabled", havingValue = "true", matchIfMissing = false)
public class ObservabilityAutoConfiguration {
    
    private String serviceName;
    private String otlpEndpoint;
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public void setOtlpEndpoint(String otlpEndpoint) {
        this.otlpEndpoint = otlpEndpoint;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public ObservationHandler<? extends Observation.Context> tracingObservationHandler(
            OpenTelemetryFactory factory) {
            
        String resolvedServiceName = serviceName;
        if (resolvedServiceName == null || resolvedServiceName.isEmpty()) {
            resolvedServiceName = System.getProperty("spring.application.name", "unknown-service");
        }
        
        String resolvedEndpoint = otlpEndpoint;
        if (resolvedEndpoint == null || resolvedEndpoint.isEmpty()) {
            resolvedEndpoint = System.getenv("OTEL_EXPORTER_OTLP_TRACES_ENDPOINT");
        }
        
        
        Resource resource = Resource.getDefault()
            .toBuilder()
            .put("service.name", serviceName)
            .build();
            
        SdkTracerProvider sdkTracerProvider =
          SdkTracerProvider.builder()
          .addSpanProcessor(SimpleSpanProcessor.create(new LoggingSpanExporter()))
          .setResource(resource)
          .build();

        OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
            .setTracerProvider(sdkTracerProvider)
            .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
            .build();
            
        Runtime.getRuntime().addShutdownHook(new Thread(sdkTracerProvider::close));
        
        /* OpenTelemetry openTelemetry = factory.createOpenTelemetry(resolvedServiceName, resolvedEndpoint); */

        Tracer otelTracer = openTelemetry.getTracer("micrometer-tracer");
        OtelCurrentTraceContext otelCurrentTraceContext = new OtelCurrentTraceContext();
        OtelTracer tracer = new OtelTracer(otelTracer, otelCurrentTraceContext, event -> {});
        OtelPropagator propagator = new OtelPropagator(openTelemetry.getPropagators(), otelTracer);
        
        return new ObservationHandler.FirstMatchingCompositeObservationHandler(
            new PropagatingSenderTracingObservationHandler<>(tracer, propagator),
            new PropagatingReceiverTracingObservationHandler<>(tracer, propagator),
            new DefaultTracingObservationHandler(tracer)
        );
    }
}