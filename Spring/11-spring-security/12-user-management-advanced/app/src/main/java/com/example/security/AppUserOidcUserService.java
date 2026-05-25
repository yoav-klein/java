package com.example.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.example.business.model.AppUser;
import com.example.business.service.UserAccountService;
import com.example.security.model.SecurityUser;

@Service
public class AppUserOidcUserService extends OidcUserService {

    @Autowired
    private UserAccountService userAccountService;
    
    @Override
    public OidcUser loadUser(OidcUserRequest request) {
        // load the OidcUser from the UserInfo endoint
        OidcUser oidcUser = super.loadUser(request);
        
        String provider = request.getClientRegistration().getRegistrationId();
        AppUser appUser = userAccountService.resolveOidcUser(provider, oidcUser);

        return new SecurityUser(appUser, oidcUser);
    }

}
