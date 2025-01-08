# Login Form Authentication
---

This demonstrates the use of Login Form authentication.

LoginForm authentication redirects the unauthenticated user to a login url. Spring Security
comes with a default login and logout pages, and we will override them.

It then uses the `username` and `password` request parameters to authenticate the user.

The `UserDetailsService` bean is used to provide details about users - usernames and passwords.
Here we use a really basic example of it.

Also, we define the `authorizeHttpRequests` method to require that any request will be
at least by an authenticated user.

In this example, for demonstration purposes, we override the default login and logout pages.

NOTES:
1. We had to disable CSRF for this to work, since we don't include CSRF in our form. this is the `.csrf(configurer -> {configurer.ignoringRequestMatchers("/**");})` part.
2. the `permitAll()` method we use on the FormLoginConfigurer and LogoutConfigurer is to allow accessing these pages for unauthenticated users.