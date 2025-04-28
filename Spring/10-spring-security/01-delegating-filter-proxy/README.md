# DeletagingFilterProxy
---

This project is just to get to know the `DelegatingFilterProxy`. What we have here is a regular Servlet application (no Spring MVC).
We can use Spring Security with this since it's not dependent on Spring MVC - Spring Security works on the level of Filters, which 
is before the MVC's `DispatcherServlet`.

The `DelegatingFilterProxy` is an implementation of `Filter` that bridges between the Servlet conatiner and an `ApplicationContext`.

In the `web.xml` file we define the `ContextLoaderListener` and pass in the location of our Spring configuration file (`app-context.xml`). This is to 
spin up the ApplicationContext.

Then, we define a `<filter>` section which points to `DelegatingFilterProxy` class. We pass in the Bean name that `DelegatingFilterProxy`
 will delegate work to. In this case, we're implementing a small `MyFilterBean` which only adds some content to the returned response.

 The `MyFilterBean` is a bean that's registered against the ApplicationContext using the `app-context.xml` file.

In reality, we'll be delegating work to `FilterChainProxy`.