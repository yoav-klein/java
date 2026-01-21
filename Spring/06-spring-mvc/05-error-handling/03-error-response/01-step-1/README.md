# Basic Example
---

So here we have a page that sends a request to the backend to the `/failing` endpoint. The controller method 
throws a `ServerErrorException`. 
By default, it returns an HTML page in the response body. But for a REST API client this is not much help is it?

So the first way to return a RFC 9457 response is to extend the `ResponseEntityExceptionHandler` class in our controller.
This can also be done using a `ControllerAdvice`.

This class implements a `@ExceptionHandler` that catches all `ErrorResonse` exceptions and translates them to a RFC 9457 
response.

Notice that the message that we pass to the `ServerErrorException` ctor is translated to the `detail` of the RFC 9457 response.