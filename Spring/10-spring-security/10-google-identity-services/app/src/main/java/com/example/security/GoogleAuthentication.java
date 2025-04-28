package com.example.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


public class GoogleAuthentication implements  Authentication {
    
    private String googleIdToken;
    private GoogleIdentityDetails identity;
    
    public GoogleAuthentication(GoogleIdentityDetails identity, String googleIdToken) {
        this.googleIdToken = googleIdToken;
        this.identity = identity;
        
    }

    @Override
    public String getName() {
        return this.identity.getName();
    }

    @Override
    public Object getCredentials() {
        return this.googleIdToken;
    }

    @Override
    public Object getDetails() {
        return this.identity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return null; }

    @Override
    public Object getPrincipal() {
        return this.identity.getEmail();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}

}
