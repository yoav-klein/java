# Listeners
---

The Servlet API provides a set of Listener interfaces which you can implement and register in order to respond
to events. The different types of Listeners correspond to different kinds of events happening in the application.

In this simple example we demonstrate the use of `ServletContextListener` which an implementation of it reacts to 
the initialization and destruction of the ServletContext, i.e. the servlet container.

## Run
---
```
$ gradle deploy
```

Look at the logs of Tomcat, you should see:
```
===== CONTEXT INITIALIZED: HelloWorld
```