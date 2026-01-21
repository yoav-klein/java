# Specific exceptions
---

In the previous example, we saw how to use the `ResponseEntityExceptionHandler` in order to return a RFC 9347 response. 
This is good as a safety net to handle ALL exceptions. But if you want to customize responses based on specific exceptions, 
you need another approach.

In this example we implement a `@ExceptionHandler` that returns a `ProblemDetail` object, which is a container for RFC 9347 response.
This causes the container to return a RFC 9347 response to the client.

The `ServerExceptionError`, as any other MVC exception, inherits from `ErrorResponseException`, which has the `getBody()` method 
which returns a `ProblemDetail`. So all you have to do if you want to customize the default one is to get it from the Exception 
object, customize it using one of the `set` methods, and return it!