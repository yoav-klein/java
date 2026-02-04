# Validating a List of objects
---

Say I have a list of `Pesron`s that I want to validate. Let's see how that works.

Ok so what I found out is that having the controller method take a `List<Person>` - I couldn't make validation work.
Only by wrapping the list with a DTO object `PersonListDTO` I could make it work, so be it.