
package com.example.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import jakarta.servlet.Filter;
import java.util.List;

@Configuration
@EnableWebSecurity // EnableWebSecurity injects the HttpSecurity bean and probably other beans as well
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            );

        SecurityFilterChain chain = http.build();
        return chain;
    }

    @Bean
    public UserDetailsService userDetailsService() {
            UserDetails yoav = User.withDefaultPasswordEncoder()
                    .username("yoav")
                    .password("yoav")
                    .roles("USER")
                    .build();
            UserDetails dikla = User.withDefaultPasswordEncoder()
                    .username("dikla")
                    .password("dikla")
                    .roles("ADMIN", "USER")
                    .build();
            UserDetails tamar = User.withDefaultPasswordEncoder()
                    .username("tamar")
                    .password("tamar")
                    .roles("ADMIN", "USER")
                    .build();
            return new InMemoryUserDetailsManager(yoav, dikla, tamar);
    }

}