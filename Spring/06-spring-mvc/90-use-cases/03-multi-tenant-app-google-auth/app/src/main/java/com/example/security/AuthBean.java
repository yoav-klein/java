package com.example.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.business.service.TenantService;

@Component("authz")
public class AuthBean {

    @Autowired
    TenantService tenantService;
    
    // For refernce of MethodSecurityExpressionOperations, see
    
    public boolean isUsersAccount(Authentication authentication, String tenantId, MethodSecurityExpressionOperations operations) {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = token.getPrincipal();
        Map<String, Object> attrs = oauth2User.getAttributes();
        String sub = (String) attrs.get("sub");

        return tenantService.isUserPartOfTenant(sub, tenantId);
    }

    public boolean isAdmin(Authentication authentication, String tenantId, MethodSecurityExpressionOperations operations) {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = token.getPrincipal();
        Map<String, Object> attrs = oauth2User.getAttributes();
        String sub = (String) attrs.get("sub");

        return tenantService.isAdmin(sub, tenantId);
    }
}
