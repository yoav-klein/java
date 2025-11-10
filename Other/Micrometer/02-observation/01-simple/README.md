# Observation API
---

This project is an example of using the Micrometer Observation API.

Observation is an object that you use to wrap a piece of code that you want to observe. You then use `ObservationHandlers` to 
specify exactly what to extract out of the observation - logs, metrics, traces.

In this example, we create a custom `ObservationHandler` that doesn't do much, and register it against the `ObservationRegistry`.
We also register the `DefaultMeterObservationHandler` to get metrics out.