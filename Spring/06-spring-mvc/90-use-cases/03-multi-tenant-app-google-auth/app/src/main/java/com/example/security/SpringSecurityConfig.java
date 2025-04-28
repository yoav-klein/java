package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.beans.factory.annotation.Value;

@PropertySource("file:/C:/Users/yoavk/.secrets/google-openid-credentials.properties")
@EnableWebSecurity(debug=true)
@ComponentScan
public class SpringSecurityConfig {

    @Value("${googleClientId}")
    private String googleClientId;

    @Value("${googleClientSecret}")
    private String googleClientSecret;

    @Autowired
    AutoRegistrationSuccessHandler autoRegisterSuccessHandler;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception {
        return http.oauth2Login(oauth2 -> oauth2.successHandler(autoRegisterSuccessHandler))
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            )
            .build();
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