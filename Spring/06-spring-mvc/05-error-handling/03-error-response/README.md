# Error Response
---

See https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html and what we wrote about it in our Google Docs.

How are errors handled when we're dealing with API requests? For this, we have RFC9457 which is a standard of structure of
error responses from servers to clients.

The flow is as such:
1. Your backend receives a request
2. The requests causes an exception to be raised (Any Exception that implements `ErrorResponse`)
3. You now want this to be translated to a RFC 9347 response.

There are several ways to do this and that's exactly what we're here for.

We'll take it in several steps, each step taking it a bit further:

**In step 1** we'll get to know the use of `ResponseEntityExceptionHandler` and how it handles MVC exceptions,

**In step 2** we'll see how to return a RFC 9457 response ourselves using a ExceptionHandler

**In step 3** We'll see how internationalization and customization of error response works

**In step 4** We'll see a more mature example of how we should customize error responses.
