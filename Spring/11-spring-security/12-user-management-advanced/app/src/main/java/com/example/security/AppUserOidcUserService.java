package com.example.security;

import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Service
public class AppUserOidcUserService extends OidcUserService {
    
    @Override
    public OidcUser loadUser(OidcUserRequest request) {
        OidcUser oidcUser = super.loadUser(request);
        
        // check if user is in the user_provider table, if so load him from the users table
        // create a AppUser instance
        
        // if not exist, add the user in the users and user_provider tables
        System.out.println("====== MY USER SERVICE ======");
        /* SecurityUser securityUser = new SecurityUser(); */
        return oidcUser;
    }
}
