# Spring MVC Test Framework (MockMvc)
---

In this project we demonstrate the use of the Spring MVC Test Framework (MockMvc)

MockMVC allows you to test your controllers without a running server. Basically it loads the `DispatcherServlet` and 
passes it with mock implemenations of the Servlet API - i.e. `HTTPServletRequest` etc. It wraps the DispatcherServlet
as if it's running in a servlet container.

The `@WebAppConfiguration` annotation must be used in order to instruct the Spring TestContext Framework to load a `WebApplicationContext`
instead of a regular `ApplicationContext`. 

The `@WebAppConfiguration` needs to know where the root of the webapp is at. The default is `file:src/main/webapp`.

The `MockMvc` class is the main entrypoint for the tests. the `perform` method is basically sending requests to the DispatcherServlet.
Then, you use _expectations_ to check the results.


