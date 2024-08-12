# Error Codes of Validation Errors
---

In the `Errors` instance that we pass to the `validate` method, the validation errors will be registered.
Each validation error (an instance of `ObjectError` or `FieldError`) has a `errorCode`. 

When you register errors in your own implementation of Spring's Validator, you specify the code for each error.

When working with Java Bean Validation, the `LocalValidatorFactoryBean` registers the error with the code 
corresponding the constraint. For example, a `@Min` validation failure will register an error with the code `Min`.

