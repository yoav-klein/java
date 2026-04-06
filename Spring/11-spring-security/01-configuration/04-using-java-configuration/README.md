# Configuring Spring Security - Java Annotations
---

This does pretty much what the previous example did, but this time using Java annotations.

In the `app-context.xml` file we just include the `<context:component-scan base-package="beans" />` element.
This will have the `@Configuration` class to be detected (`SpringConfig`), which will create the necessary beans.

The `@EnableWebSecurity` does the heavy lifting of conifguring important beans like the `HttpSecurity` and also `FilterChainProxy`.
Inside, we create a `SecurityFilterChain` bean which will be used by `FilterChainProxy`.

