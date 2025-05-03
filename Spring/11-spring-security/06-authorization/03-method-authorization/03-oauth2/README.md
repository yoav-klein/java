# Programmatic Authorization - OAuth2
---

In this project, we demonstrate the usage of method authorization in conjunction with OAuth2. We'll use Google authentication for this obviously.

In this small application, each user has a account (like a bank account). Each user can pull and push money to his account.

Obviously, only the account owner has authorization to access to his account. So, access to an account by an unauthorized user
must result in a 403 response, and prison :).

We demonstrate how to implement this using the programmatic approach, and using only SpEL. See the `AccountService` class.

