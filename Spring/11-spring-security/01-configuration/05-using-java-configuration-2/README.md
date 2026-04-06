# Configuring Spring Security - Java Annotations 2
---

Here we take it a step forward. Instead of declaring the `DelegatingFilterProxy` as a filter in the `web.xml`, we have the `SpringConfig` class inherit from `AbstractSecurityWebApplicationInitializer`. This makes the `DelegatingFilterProxy` be registered against the Servlet container wired to the `FilterChainProxy`.

Additionally, we don't need to load the ApplicationContext in the `web.xml`, since the `AbstractSecurityWebApplicationInitializer` can take a `Configuration` class and initialize the WebApplicationContext by itself.