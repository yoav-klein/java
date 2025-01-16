# Configuring Spring Security - Java Annotations
---

This does pretty much what the previous example did, but this time using Java annotations.

In the `app-context.xml` file we just include the `<context:annotation-config />` and `<context:component-scan base-package="beans" />` elements.
From there the `SpringConfig` class will take control.

The `@EnableWebSecurity` does the heavy lifting of conifguring important beans like the `HttpSecurity` and probably also `FilterChainProxy`.
Inside, we create a `SecurityFilterChain` bean which will be used by `FilterChainProxy`.

