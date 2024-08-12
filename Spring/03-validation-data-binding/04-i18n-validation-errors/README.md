# i18n of Validation Errors
---

This builds on the `03-error-code` example. There, we used the `org.springframework.validation.Validator` interface to validate
the Person object. Recall that there, upon validation, the `Errors` object was populated with `FieldError`s and `ObjectError`s
that each had an _error code_.

When using a `DataBinder` to do the validation, there is another layer. A DataBinder object is associated with a `MessageCodesResolver` object.
By default, this is the `DefaultMessageCodesResolver`. This object takes an error code and adds some other error codes. When you 
use the `DataBinder.validate()`, the `BindingResults` object (which inherits from `Errors`) contains errors that their
error codes are already added with the additional error codes.

Each Error instance is a MessageSourceResolvable, so we can pass it to a `MessageSource.getMessage` 