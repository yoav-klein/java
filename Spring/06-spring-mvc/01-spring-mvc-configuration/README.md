# Spring MVC Configuration
---

In these subdirectories we will demonstrate the different ways to configure Spring MVC.

This divides into 2:
* The way we register the DispatcherServlet with the Servlet container
* The way we configure the _MVC Config_, which is the WebApplicationContext which will be used by the DisptacherServlet.

The first example `xml` demonstrates registering the DispatcherServlet using the good old `web.xml` file. The MVC Config is configured using a XML file.
The second example `web-application-initializer` demonstrates implementing the `WebApplicationInitializer` to register the 
DispatcherServlet, but still uses XML file to configure the MVC Config.
The third example `abstract-dispatcher-servlet-initializer` demonstrates the use of `AbstractDispatcherServletInitializer`, which is more
specific to registering ServletDispatcher with the Servlet container. Still uses XML to configure the MVC Config
The fourth example `java-based-config` uses `AbstractAnnotationConfigDispatcherServletInitializer` to register the DispatcherServlet 
which in turn is configured using Java configuration, not with XML.
