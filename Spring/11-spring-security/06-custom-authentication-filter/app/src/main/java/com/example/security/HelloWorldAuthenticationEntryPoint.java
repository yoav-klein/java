
package com.example.security;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.security.web.DefaultRedirectStrategy;

import java.io.IOException;

@Component
public class HelloWorldAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private String loginUrl;

    public HelloWorldAuthenticationEntryPoint(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException authException) throws IOException {
        DefaultRedirectStrategy redirect = new DefaultRedirectStrategy();

        redirect.sendRedirect(request, response, loginUrl);
    }
}