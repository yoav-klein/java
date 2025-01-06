# Configuring Spring Security - Using the XML Namespace
---

In this project we use the `<security:*>` XML namespace in the ApplicationContext configuration. This allows us to easily
configure security for our application, as the Spring Security module will automatically read these elements and create the
required beans alone.

Note that in this example, we don't manually configure the `FilterChainProxy`. When we include the `<security:http />` element,
the framework creates the `FilterChainProxy`, wiring it to the `DelegatingFilterProxy` for us.

There are many thing in the XML that we haven't talked about yet, but we'll be discussed later.