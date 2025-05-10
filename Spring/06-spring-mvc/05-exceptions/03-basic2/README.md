# Exception Handling
---

There are several ways to handle exceptions thrown during request processing.

There is a list of Exception Resolvers that attempt to resolve exceptions.

## ExceptionHandlerExceptionResolver
Resolves exceptions by invoking an `@ExceptionHandler` method in a @Controller or a @ControllerAdvice class.
you can declare ExceptionHandler methods that will be invoked for specific exception types
This is what we do in the `NoSuchUserException` example

## ResponseStatusExceptionResolver
Resolves exceptions with the @ResponseStatus annotation and maps them to HTTP status codes based on the value in the annotation.

## SimpleMappingExceptionResolver
Allows mapping exception types to view names.
This is done by creating a `SimpleMappingExceptionResolver` bean. See in the `SpringWebConfig.java` how it's done.
