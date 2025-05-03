
package org.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;


import java.util.Map;

import org.example.business.service.AccountService;

@Component("authz")
public class AuthBean {

    @Autowired
    AccountService accountService;
    
    // For refernce of MethodSecurityExpressionOperations, see
    
    public boolean isUsersAccount(Authentication authentication, String id, MethodSecurityExpressionOperations operations) {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = token.getPrincipal();

        Map<String, Object> attrs = oauth2User.getAttributes();
        String sub = (String) attrs.get("sub");

        System.out.println("sub: " + sub + " id: " + id);
        
        if(sub.equals(id)) return true;
        return false;
    }
}
