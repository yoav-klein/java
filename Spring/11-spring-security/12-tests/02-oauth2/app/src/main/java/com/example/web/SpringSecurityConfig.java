package com.example.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.config.web.PathPatternRequestMatcherBuilderFactoryBean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@PropertySource("file:/C:/Users/yoavk/.secrets/google-openid-credentials.properties")
@EnableWebSecurity // (debug=true)
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Value("${googleClientId}")
    private String googleClientId;

    @Value("${googleClientSecret}")
    private String googleClientSecret;

    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception {
        return http.oauth2Login(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/admin").hasRole("ADMIN")
            .requestMatchers("/management").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .build();
    }

    @Bean
    PathPatternRequestMatcherBuilderFactoryBean usePathPattern() {
        return new PathPatternRequestMatcherBuilderFactoryBean();
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
