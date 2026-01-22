# Add custom fields to the ProblemDetail
---

You can add custom fields to the `ProblemDetail` object, which end up in the RFC 9457 JSON.

There are several ways doing this, but here we demonstrate how to hook in the middle of the process of the `ResponseEntityExceptionHandler`.
The `handleExceptionInternal` takes care of calling MessageSource to localize the body, so we want to get that benefit from it.
So we override the specific method that handles our specific exception that we want to take care of.