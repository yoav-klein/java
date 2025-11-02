# DeferredResult
---

An example of usage of `DeferredResult`. Very simple, calling the `/long` endpoint will create a new Thread that sleeps for 5 seconds
before writing to the DeferredResult. The controller method returns immediately. Look at the output of the Tomcat instance, and you'll see
that the printing of `I'm done` is immediate, while the response to the browser is only after 5 secs.