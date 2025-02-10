
package com.example.security;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.DelegatingSecurityContextRepository ;

import org.springframework.context.annotation.Bean;
// import org.springframework.security.config.Customizer;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private static final String clientId = "488379779056-8tfibuh2fek5jv0f5pf49db1efm75e9i.apps.googleusercontent.com";

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of(new GoogleAuthenticationProvider(clientId)));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf((csrf) -> csrf
                .ignoringRequestMatchers("/**")
            )
            .addFilterBefore(new GoogleAuthenticationFilter("/login/google/response", authenticationManager()), LogoutFilter.class)
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(new GoogleAuthenticationEntryPoint())
            )
            .securityContext(securityContext -> securityContext
			    .securityContextRepository(new DelegatingSecurityContextRepository(
                    new RequestAttributeSecurityContextRepository(),
                    new HttpSessionSecurityContextRepository()
                ))
            )
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login/google-sign-in").permitAll()
                .anyRequest().authenticated()
            )
           .build();
    }


}