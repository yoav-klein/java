# Basics
---

In this project we demonstrate a simple use case. We apply the `@PreAuthorize` annotation on the `FooService.someImportantMethod`
to enforce that only users that have the `ADMIN` role can acceess it.

So if you try to login with `user`, you'll get a 403. If you login with `admin`, you won't.