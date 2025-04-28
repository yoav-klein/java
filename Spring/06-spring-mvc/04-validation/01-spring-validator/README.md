# Validating using Spring's Validator Interface
---

This example demonstrates the use of `org.springframework.validation.Validator` to perform validation on user input.

We create a `EmailFormValidator` that implements this interaface and provied our custom validation logic.
We then register this validator using the `@InitBinder` annotated method.

Annotating a @ModelAttribute with @Validated will make the Framework validate the input using the Validator we've registered.