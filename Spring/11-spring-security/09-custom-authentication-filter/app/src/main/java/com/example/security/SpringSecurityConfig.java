package com.example.security;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.context.annotation.Bean;
// import org.springframework.security.config.Customizer;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of(new HelloWorldAuthenticationProvider()));
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.ignoringRequestMatchers("**"))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(new HelloWorldAuthenticationEntryPoint("/login"))
            )
            .addFilterBefore(new HelloWorldAuthenticationFilter("/authenticate", authenticationManager()), LogoutFilter.class)
            .build();
    }
    
}
