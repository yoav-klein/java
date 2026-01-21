# Customization and i18n of ProblemDetail fields
---

See https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html#mvc-ann-rest-exceptions-i18n

Each `ErrorResponse` (like `ServerErrorException`) exposes message codes for `type`, `title` and `detail`. `ResponseEntityExceptionHandler ` resolves these through a `MessageSource` and updates the corresponding `ProblemDetail` fields accordingly.

See the link above.

In this example we demonstrate this capability.