# Validation using Java Bean Validation
---

Spring provides support in Jakarta _Java Bean Validation_ specification.
We use the constraints annotations like @Min, @Max, @Size, etc in the model classes themselves to impose constraints.

For this, we need to create a bean of type `LocalValidatorFactoryBean`. We then autowire it to our Controller class.


## Behaviors

* If a command object has `@Valid` or `@Validated` and validation fails - `MethodArgumentNotValidException`
* If a method parameter has a Constraint annotation (`@Min`, `@Max`, etc.):
    * If that argument fails validation - `HandlerMethodValidationException`
    * If another command object has `@Validated` and validation fails: `MethodArgumentNotValidException`
    * If another command object has `@Valid` and validation fails: `HandlerMethodValidationException`