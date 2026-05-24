
package com.example.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity(debug=true)
@EnableMethodSecurity
@ComponentScan
@PropertySource("file:/C:/Users/yoavk/.secrets/google-openid-credentials.properties")

public class SpringSecurityConfig {
    @Value("${googleClientId}")
    private String googleClientId;

    @Value("${googleClientSecret}")
    private String googleClientSecret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults())
            /* .formLogin(Customizer.withDefaults()) */
            .oauth2Login(oauth2 -> 
                oauth2.loginPage("/login")
            )
            .logout(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> { authorize
                .requestMatchers("/management").hasRole("ADMIN")
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated();
            });

        SecurityFilterChain chain = http.build();
        return chain;
    }

    /* @Bean
    public UserDetailsService userDetailsService() {
            UserDetails user = User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("password")
                    .roles("USER")
                    .build();
            UserDetails admin = User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("password")
                    .roles("ADMIN", "USER")
                    .build();
            return new InMemoryUserDetailsManager(user, admin);
    } */


    /* private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
           .clientId(googleClientId)
           .clientSecret(googleClientSecret)
           .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
           .issuerUri("https://accounts.google.com")
           .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
           .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
           .redirectUri("{baseUrl}/login/oauth2/code")
           .userNameAttributeName("sub")
           .scope("openid", "email", "profile")
           .tokenUri("https://www.googleapis.com/oauth2/v4/token")
           .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
           .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
           .build();
    } */

    private ClientRegistration googleClientRegistration() {
        return ClientRegistrations.fromIssuerLocation("https://accounts.google.com")
           .clientId(googleClientId)
           .clientSecret(googleClientSecret)
           .registrationId("google")
           .redirectUri("{baseUrl}/login/oauth2/code/google")
           .userNameAttributeName("sub")
           .scope("openid", "email", "profile")
           .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
           .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
           .build();
    }

    @Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
	}
}