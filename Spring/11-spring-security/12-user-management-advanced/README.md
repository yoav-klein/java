# User Management - Advanced
---

This project is a reference for a mature user management system.
It's an application that allows a user to sign up using OAuth2/OIDC providers, but it has 
a User model of its own, so that the user management can be extended to other methods as well.


PLAN:

Have `SecurityUser implements OidcUser`
Have `AppUser` - reflecst the database user table
`SecurityUser` holds `OidcUser` and `AppUser`.
A special `OidcUserService` that returns a `SecurityUser`.
The controller methods take a `SecurityUser` and read its `getAppUser`, and work with it.



Flows:
* A user logs in - you check if he's in the user_provider table.
    * If so, you take his user_id, and log him in with this, done.
* If he's not in user_provider, you check if his mail is in `users`.
    * If so, you add an entry in `user_provider`, done. (link accounts)
* If he's not there, you create an entry in `users` and in `user_provider`
