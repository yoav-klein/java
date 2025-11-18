# Observability - Tracing integration
---

In this demo we demonstrate how to create traces using the Observation API.
We configure a OpenTelemetry instance, and hook it into the Micrometer registry.
In the HTTP server handler, we create an observation, which will create a span.

In this example we use the Console Span Processor - outputs spans to the console.
