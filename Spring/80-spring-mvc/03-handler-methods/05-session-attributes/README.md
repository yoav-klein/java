# Session Attributes
---

The `@SessionAttributes` annotation is used to annotate a controller. You specify the model attributes that you want to be
persisted across requests during a session.

In this example, we have a multi-stage registration form. Each form's `next` button is a new HTTP request, but we want
to keep the same User object across these requests.

The value passed to the `@SessionAttributes` annotation specifies the names or types of the session attributes that will be persisted.