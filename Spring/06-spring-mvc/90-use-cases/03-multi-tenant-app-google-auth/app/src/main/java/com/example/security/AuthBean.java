package com.example.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.example.business.service.TenantService;

@Component("authz")
public class AuthBean {

    @Autowired
    TenantService tenantService;
    
    public boolean isUserPartOfTenant(Authentication authentication, String tenantId) {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = token.getPrincipal();
        Map<String, Object> attrs = oauth2User.getAttributes();
        String sub = (String) attrs.get("sub");

        return tenantService.isUserPartOfTenant(sub, tenantId);
    }

    public boolean isAdmin(Authentication authentication, String tenantId) {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = token.getPrincipal();
        Map<String, Object> attrs = oauth2User.getAttributes();
        String sub = (String) attrs.get("sub");

        return tenantService.isAdmin(sub, tenantId);
    }

    public boolean isUser(Authentication authentication, String userId) {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = token.getPrincipal();
        Map<String, Object> attrs = oauth2User.getAttributes();
        String sub = (String) attrs.get("sub");

        return sub.equals(userId);
    }
}
