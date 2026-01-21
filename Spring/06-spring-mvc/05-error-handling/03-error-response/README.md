# Validating REST API requests
---

In this example we demonstrate how it works when you want to validate objects that are received in API requests.

See https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html

So it works like this:
1. We send an API request with bad arguments
2. A `MethodArgumentNotValidException` exception is raised
3. Since we extend the `ResponseEntityExceptionHandler` class in a `@ControllerAdvice`, the exception is handled by a handler that returns a `ProblemDetail` instance
4. This causes the container to return a RFC9457-compatible response.

NOTE: We also do `i18n` of the error response. See in the link above about it.

NOTE: We are also customizing the validation error messages, see https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html#validation-beanvalidation-spring-method-i18n
