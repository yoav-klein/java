# Configuration
---

In this directory and sub-directories we'll explore the configuration of Spring Security. Basically it's the stuff described in the _Architecture_ page of Spring Security docs - The `DelegatingFilterProxy`, `FilterChainProxy`, `SecurityFilterChain`.

In all the following examples, what eventually happens is this:

1. The Servlet container uses Spring's `ContextLoaderListener` to load the `ApplicationContext`.
2. A Servlet `Filter` of type `DelegatingFilterProxy` is registered against the Servlet container. 
3. The `DelegatingFilterProxy` is taking a bean name as argument - this bean is a Filter to which it'll _delegate_ work to.
4. After example 01 - The `FilterChainProxy` is the bean that will be delegated work to. In example 02 we explicitly create this bean, but that's just for demonstration.
5. After example 02 - The Spring Security infrastructure (FilterChainProxy, SecurityFilterChain, etc.) is created automatically. We configure Spring Security declaratively.
