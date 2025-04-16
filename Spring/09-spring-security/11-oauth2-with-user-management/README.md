# OAuth2 with User Management
---

This builds on `08-spring-mvc-oauth2`. In this project we add user management, so that we 
manage Google-authenticated users in our own database.

This is done by providing an implementation of `AuthenticationSuccessHandler`, which checks if the 
user is in our database, and registers it if not.
We register this success handler using the `successHandler` method of `OAuth2LoginConfigurer`.

The `UserManager` class mocks a real database where we store user information.

