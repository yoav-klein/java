package com.example.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import org.springframework.security.core.Authentication;

import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


public class HelloWorldAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public HelloWorldAuthenticationFilter(String filterProcessesUri, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(filterProcessesUri, "POST"), authenticationManager);

        this.setSecurityContextRepository(new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository()
        ));
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        
        System.out.println("In attemptAuthentication");
        String code = request.getParameter("code");
        System.out.println("code: " + code);

        HelloWorldAuthenticationToken token = new HelloWorldAuthenticationToken(code);
        
        return this.getAuthenticationManager().authenticate(token);
    }
}
