# Login Form Authentication
---

This demonstrates the use of Login Form authentication.

LoginForm authentication redirects the unauthenticated user to a login url, by deafult `/login`.
It then uses the `username` and `password` request parameters to authenticate the user.

The `UserDetailsService` bean is used to provide details about users - usernames and passwords.
Here we use a really basic example of it.

Also, we define the `authorizeHttpRequests` method to require that any request will be
at least by an authenticated user.