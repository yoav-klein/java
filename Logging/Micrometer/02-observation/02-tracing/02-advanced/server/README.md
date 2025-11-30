# Tracing using Observability
---

In this project we demonstrate how to hook into the Observability API to configure tracing.

Spring is already instrumented with the Observability API, so it creates _Observations_. You can create an `ObservationHandler` of type
`TracingObservationHandler`. This will have the Observations emit spans.