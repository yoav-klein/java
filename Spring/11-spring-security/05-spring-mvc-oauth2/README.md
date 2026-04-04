# OAuth2 with Spring MVC
---

This is a Spring MVC application that uses OAuth2 (more specifically - OpenID Connect) to authenticate users.

## Structure
We have 3 Spring configurations here:
1. `SpringBusinessConfig` - this is a root WebApplicationContext, that may include service and repository level beans that
theoretically needs to be shared across several Servlets (if you have them)
2. `SpringWebconfig` - this includes web-level beans (view resolving, stuff like that), and this is specific to the DispatcherServlet.
3. `SpringSecurityConfig` - here we define all the security-related beans. This is also part of the root config classes. This MUST be in the root config classes
since the filters must be created before the servlet.. something like this..

## Capabilites demonstrated
There are several capabilities we demonstrate here.

First, of course, allowing users to log in using Google. 

Then, in the controller method, we take `@AuthenticationPrincipal Object user` parameter, which is essentially `Authentication.getPrincipal()`. This returns the 
authenticated principal.  In case of OAuth2, this is a `OAuth2User`. In case of form login, this is a `User`.

### Acessing the AccessToken
In case of OAuth2 login, we can access the AccessToken. This is done by Autowiring the `OAuth2AuthorizedClientService` to the Controller class.

Recall, that in order to retrieve the `OAuth2AuthorizedClient` we need to pass the ClientRegistration ID and the principal's name to the `loadAuthorizedClient` method.

So, we take a `Authentication authentication` parameter, which lets us access the principal name. We know that the client registration ID is `google`, so we can access
the `OAuth2AuthorizedClient`, which gives us access to the AccessToken.
