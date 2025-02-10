package com.example.security;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class GoogleAuthenticationIdToken extends AbstractAuthenticationToken {
    private final String googleIdToken;

    public GoogleAuthenticationIdToken(String googleIdToken) {
        super(null);
        this.googleIdToken = googleIdToken;
        setAuthenticated(false);
    }

    public String getIdToken() {
        return this.googleIdToken;
    }

    @Override
    public Object getPrincipal() {
        return this.googleIdToken;
    }

    @Override
    public Object getCredentials() {
        return this.googleIdToken;
    }
}

