package com.example.security;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import org.springframework.security.authentication.BadCredentialsException;


public class HelloWorldAuthenticationProvider implements AuthenticationProvider {

    @Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        HelloWorldAuthenticationToken token = (HelloWorldAuthenticationToken)authentication;
        if( ((String)token.getCredentials()).equals("banana")) {
            authentication.setAuthenticated(true);
            return authentication;
        } else {
            throw new BadCredentialsException("Bad ID token");
        }
    }

    @Override
	public boolean supports(Class<?> authentication) {
		return HelloWorldAuthenticationToken.class.isAssignableFrom(authentication);
	}

}