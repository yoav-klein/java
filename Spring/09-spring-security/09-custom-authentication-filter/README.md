# Custom Authentication Filter
---

This project is a demonstration of creating your own authentication filter.

This came handy when I wanted to use Google Identity Services to authenticate users using their Google accounts (this is not the regular OpenID Connect flow). See in the next project.

## What is it
---
It's a simple authentication filter which authenticates you if you just type in `banana` in the login page.

## How it Works
---

First we create an AuthenticationFilter - `HelloWorldAuthenticationFilter`. This filter intercepts HTTP POST requests to `/authenticate` endpoint.
We have an AuthenticationProvider `HelloWorldAuthenticationProvider` which authenticates instances of `HelloWorldAuthenticationToken`.

The filter extracts the `code` paramater from the POSTed form, wraps it in `HelloWorldAuthenticationToken` and calls `ProviderManager.authenticate(token)`.

In `SpringSecurityConfig` we create a `ProviderManager`, passing it an instace of `HelloWorldAuthenticationProvider`.

Also, we register an `AuthenticationEntryPoint` which redirects unauthenticated requests to the login page.

## Request Flow
---

The user tries to browse to our app. The request is not yet authenticated, so `AuthorizationFilter` throws an `AccessDeniedException`. Then our `HelloWorldAuthenticationEntryPoint` is used to redirect the user to the login form.

When the user submits the form, it's intercepted by our `HelloWorldAuthenticationFilter`. The filter extracts the `code` parameter from the form,
 wraps it in `HelloWorldAuthenticationToken` and calls `ProviderManager.authenticate(token)`. 

 The `ProviderManager` finds our `HelloWorldAuthenticationProvider`, passing it the `HelloWorldAuthenticationToken`. The AuthenticationProvider checks
 if the code is `banana`, and if so, authenticates the request.

 ## NOTES
 ---

Note that in the `HelloWorldAuthenticationFilter`'s constructor we register a `SecurityContextRepository` in order to set the `HttpSessionSecurityContextRepository`, since this is what enables the correlation of HTTP session to an Authentication object.

For some reason I didn't find out yet, this is needed, although I didn't see it being done in other extensions of `AbstractAuthenticationProcessingFilter` like `UsernamePasswordAuthenticationFilter`.
