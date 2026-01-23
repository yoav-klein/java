# Validation errors
---

In this demo we'll see how to handle validation errors.

So based on step 4 - we override the specific method we need from `ResponseEntityExceptionHandler`, in this case it's the one that handles `MethodArgumentNotValidException`. Great.

Now, we want to add an `errors` section which is a list of objects, each has a `field` and `reason`, so that the frontend can parse this 
and display each reason next to its input. So the `MethodArgumentNotValidException` has the `getFieldErrors` which returns a list of `FieldError` objects.
The `FieldError` has a `getField` method which is the field itself, and it is a `MessageSourceResolvable`, so it can be passed as is to a `MessageSource`.
That's how we do i18n for the error reasons.


