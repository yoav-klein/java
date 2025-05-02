
package org.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.beans.factory.annotation.Value;

@PropertySource("file:/C:/Users/yoavk/.secrets/google-openid-credentials.properties")
@Configuration
@EnableWebSecurity(debug=true)
@EnableMethodSecurity
@ComponentScan
public class SpringSecurityConfig {
    @Value("${googleClientId}")
    private String googleClientId;

    @Value("${googleClientSecret}")
    private String googleClientSecret;

    @Autowired
    AutoRegistrationSuccessHandler autoRegisterSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.oauth2Login(oauth2 -> oauth2.successHandler(autoRegisterSuccessHandler))
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

        SecurityFilterChain chain = http.build();
        return chain;
    }

    private ClientRegistration googleClientRegistration() {
		return CommonOAuth2Provider.GOOGLE.getBuilder("google")
			.clientId(googleClientId)
			.clientSecret(googleClientSecret)
			.build();
	}

    @Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
	}

    // we create this bean so it'll be available in the Controller class
    // so we can access the AccessToken
    @Bean
	public OAuth2AuthorizedClientService authorizedClientService(
			ClientRegistrationRepository clientRegistrationRepository) {
		return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
	}

}