# Spring-driven Method Validation
---

See https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html#validation-beanvalidation-spring-method

In this example we demonstrate method validation. In order to have method validation you must:
1. Configure the `MethodValidationPostProcessor` bean
2. Annotated the target class with `@Validted`

Several things we demonstrate here:
1. In the `myValidMethod` we demonstrate the basics of method validation.
2. In `takeValidUser` we demonstrate using `@Valid` to validate method parameters using Bean validation - i.e. the `User` class has Java Bean Validation annotations on its fields.
3. In `takeValidUsers` we demonstrate validating a List of objects that each is validated
4. We also demonstrate the translation of the default `ConstraintViolationException` to `MethodValidationException` in the AppConfig.

## Messages

When you configure the `MethodValidationPostProcessor` to throw `MethodValidationException`s, then this exception gives you a 
`List<ParameterValidationResult>`. Each of these represents a failed parameter validation.
The `ParameterValidationResult` exposes a `getResolvableErrors()` method that returns a `List<MessageCodeResolvable>`, so you can pass those to a `MessageSource` 
and get localized error messages.