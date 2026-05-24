package com.example.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.Map;

import com.example.business.model.AppUser;

public class SecurityUser implements OidcUser {
    private AppUser appUser;
    private OidcUser oidcUser;

    public SecurityUser(AppUser appUser, OidcUser oidcUser) {
        this.appUser = appUser;
        this.oidcUser = oidcUser;
    }

    public SecurityUser() {}

    public AppUser getAppUser() {
        return this.appUser;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.oidcUser.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.oidcUser.getAuthorities();
    }

    @Override
    public String getName() {
        return appUser.getId();
    }

    @Override
    public Map<String, Object> getClaims() {
        return this.oidcUser.getClaims();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return this.oidcUser.getUserInfo();
    }

    @Override
    public OidcIdToken getIdToken() {
        return this.oidcUser.getIdToken();
    }

}
