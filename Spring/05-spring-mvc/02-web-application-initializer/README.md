# Using WebApplicationInitializer
---

On top of the basic example, here instead of using the `web.xml` file, we provide
an implementation of the WebApplicationInitializer interface. This implementation will be
auto-detected and run by the Servlet container. In the `onStartup` method, we create the 
DispatcherServlet and register it with the Servlet container.