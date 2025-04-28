# @ModelAttribute - Binding Errors
---

This example demonstrates how binding errors are handled.

Basically, when you use a @ModelAttribute, data binding is done.
The attempt is to bind according to the names of the request parameters to the 
names of the target object properties.

If after the @ModelAttribute-annotated parameter you get a BindingResult parameter, then it will be populated
with binding errors, if any.

Otherwise - an exception will be raised

When used on a method parameter, it is used to bind request parameters to model objects.
When used on a method, the method will be called before the call to the handler method, to populate
the model.

