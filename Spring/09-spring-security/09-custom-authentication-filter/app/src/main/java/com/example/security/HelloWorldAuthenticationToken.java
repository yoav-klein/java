package com.example.security;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class HelloWorldAuthenticationToken extends AbstractAuthenticationToken {
    private String token;

    public HelloWorldAuthenticationToken(String token) {
        super(null);
        this.token = token;
        setAuthenticated(false);
    }

    @Override
    public Object getPrincipal() {
        return "johnSmith";
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}