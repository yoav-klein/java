# Context Hierarchies
---

In this project we demonstrate Context Hierarchies. We have 2 Application Contexts: One configuration configures web-tier beans, and one for the business tier.
The `SpringBusinessConfig` is loaded by the `getRootConfigClasses`. This includes the service and data layer beans, which usually needs to be shared across
multiple Servlets. The web tier `SpringWebConfig` is unique to the DispatcherServlet and is loaded by the `getServletConfigClasses`