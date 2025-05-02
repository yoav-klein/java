# Programmatic Authorization
---

Sometimes SpEL expressions are not enough for us and we need a more sophisticated authorization rules.
In this case, we can create a custom Bean which exoses a method(s) in which we implement authorization logic.
Then, we can refer to these methods in the SpEL expressions that we put in the `@Pre` or `@Post` annotations.

In this example, we just demonstrate how to implement this, what we have access to in those methods, etc.