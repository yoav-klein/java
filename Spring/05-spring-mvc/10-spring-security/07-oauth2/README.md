# OAuth2
---

This project demonstrates using OpenID Connect login. We demonstrate many configuration options just for 
learning purposes, but generally you don't have to use them all, you can just stick with the defaults.

Prerequisites: have the `C:/Users/yoavk/.secrets/google-openid-credentials.properties` file which contains your google Client ID and secret.

Let's go over some of the configurations:

```
.loginPage("/myloginpage")
```

Here we create a custom Login page. Spring Security will generate a default one if you leave this blank. The Login page 
presents a link to each registered authentication provider. Basically this link redirects back to the application to the URI
that will be captured by `OAuth2AuthorizationRequestRedirectFilter`, which will prepare a request to the authorization server 
and redirect the user there.

```
.authorizationEndpoint(authorization -> authorization.baseUri("/mycustom/oauth2/authorization"))
```

This is the base of those URLs that the Login page will redirect to. All requests to this base URL will be captured 
by the above-mentioned filter. There's an endpoint for each configured authentication provider (`/mycustom/oauth2/authorization/google`, `/mycustom/oauth2/authorization/okta`, etc.). 


```
.redirectionEndpoint(redirection -> redirection.baseUri("/mycustom/oauth2/code/*"))
```

This is the wildcard URLs that will be captured by `OAuth2LoginAuthenticationFilter`. Requests to URLs with this base will be redirects
from the authentication providers' authorization servers, so they will contain the authorization code. The Filter will extract this authorization code
and use it to request the access token, ID token, and refresh token.

NOTE: If you customize this URL, you need to make sure to customize it for each registered client ClientRegistration also.

```
.tokenEndpoint(token -> token.accessTokenResponseClient(new RestClientAuthorizationCodeTokenResponseClient()))
```

This lets you customize the `ResponseClient` which is the client that will make the request to the Token Endpoint. As with other configurations here, generally stick with the default.


### Client regisrtation
```
 private ClientRegistration googleClientRegistration() {
        
        return ClientRegistration.withRegistrationId("google")
           .clientId(googleClientId)
           .clientSecret(googleClientSecret)
           .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
           .issuerUri("https://accounts.google.com")
           .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
           .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
           .redirectUri("{baseUrl}/mycustom/oauth2/code/{registrationId}")
           .userNameAttributeName("sub")
           .scope("openid", "email", "profile")
           .tokenUri("https://www.googleapis.com/oauth2/v4/token")
           .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
           .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
           .build();
   }
```

This is all you can configure for an authentication provider. We could and should just use the shorthand:
```
private ClientRegistration googleClientRegistration() {
		return CommonOAuth2Provider.GOOGLE.getBuilder("google")
			.clientId(googleClientId)
			.clientSecret(googleClientSecret)
			.build();
	}
```

