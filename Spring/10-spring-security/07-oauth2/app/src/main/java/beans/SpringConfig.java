
package beans;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.RestClientAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.client.endpoint.AbstractOAuth2AuthorizationGrantRequest;

import java.net.http.HttpHeaders;
import java.util.logging.*;

@Configuration
@PropertySource("file:/C:/Users/yoavk/.secrets/google-openid-credentials.properties")
@EnableWebSecurity(debug=true)
public class SpringConfig {
    @Value("${googleClientId}")
    private String googleClientId;

    @Value("${googleClientSecret}")
    private String googleClientSecret;

    Logger logger = Logger.getLogger("MyLogger");

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception {
        http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/myloginpage").permitAll()
            .anyRequest().authenticated()
        )
        .oauth2Login(oauth2 -> oauth2
            .loginPage("/myloginpage")
            .redirectionEndpoint(redirection -> redirection.baseUri("/mycustom/oauth2/code/*"))
            .authorizationEndpoint(authorization -> authorization.baseUri("/mycustom/oauth2/authorization"))
            .tokenEndpoint(token -> token.accessTokenResponseClient(new RestClientAuthorizationCodeTokenResponseClient()))
        );
        return http.build();
    }

    /*
    // A shortcut for creating google ClientRegistration, using the CommonOAuth2Provider class

    private ClientRegistration googleClientRegistration() {
		return CommonOAuth2Provider.GOOGLE.getBuilder("google")
			.clientId(googleClientId)
			.clientSecret(googleClientSecret)
			.build();
	} */

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

   private ClientRegistration microsoftClientRegistration() {
        
        return ClientRegistration.withRegistrationId("microsoft")
            .clientId("xxx")
            .clientSecret("xxx")
            .authorizationUri("https://login.microsoftonline.com/3bfb3df7-6b1e-447a-8dfc-cac205f2e79f/oauth2/v2.0/authorize")
            .issuerUri("https://login.microsoftonline.com/3bfb3df7-6b1e-447a-8dfc-cac205f2e79f/v2.0")
            .jwkSetUri("https://login.microsoftonline.com/3bfb3df7-6b1e-447a-8dfc-cac205f2e79f/discovery/v2.0/keys")
            .userInfoUri("https://graph.microsoft.com/oidc/userinfo")
            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
            .userNameAttributeName("name")
            .scope("openid", "email", "profile")
            .tokenUri("https://login.microsoftonline.com/3bfb3df7-6b1e-447a-8dfc-cac205f2e79f/oauth2/v2.0/token")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .build();
}


    @Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(this.googleClientRegistration(), this.microsoftClientRegistration());
	}
/* 
    @Bean
	public OAuth2AuthorizedClientService authorizedClientService(
			ClientRegistrationRepository clientRegistrationRepository) {
		return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
	}

	@Bean
	public OAuth2AuthorizedClientRepository authorizedClientRepository(
			OAuth2AuthorizedClientService authorizedClientService) {
		return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
	} */


}