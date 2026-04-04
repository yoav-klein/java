package com.example.security;

import java.io.IOException;
import java.util.Collections;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.DelegatingSecurityContextRepository ;


import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

public class GoogleAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    
    public GoogleAuthenticationFilter(String filterProcessesUri, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(filterProcessesUri, "POST"), authenticationManager);

        this.setSecurityContextRepository(new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository()
        ));
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	throws AuthenticationException, IOException, ServletException {
        System.out.println("In attemptAuthentication");
        
        System.out.println(request.getParameter("credential"));
        String idTokenString = request.getParameter("credential");

        GoogleAuthenticationIdToken googleAuthenticationIdToken = new GoogleAuthenticationIdToken(idTokenString);
        
        GoogleAuthentication authenticationResult = (GoogleAuthentication) this
			.getAuthenticationManager()
			.authenticate(googleAuthenticationIdToken);
        
        System.out.println("In attemptAuthenticationL After call to authenticate");
        
        return authenticationResult;

    }
}
