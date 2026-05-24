package com.example.security;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.business.repository.UserRepository;
import com.example.business.model.AppUser;
import com.example.security.model.SecurityUser;

@Service
public class AppUserOidcUserService extends OidcUserService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public OidcUser loadUser(OidcUserRequest request) {
        // load the OidcUser from the UserInfo endoint
        OidcUser oidcUser = super.loadUser(request);

        String provider = request.getClientRegistration().getRegistrationId();
        String subject = oidcUser.getSubject();
        
        // check if user is in the user_provider table, if so load him from the users table
        // create a AppUser instance
        
        // if not exist, check if email exists
            // if exists - link accounts (add to user_provider)
            // else - register user
        AppUser appUser = userRepository.findByProviderAndSubject(provider, subject)
            .orElseGet(() -> {
                AppUser ret = userRepository.findUserByEmail(oidcUser.getEmail()).orElseGet(() -> registerUser(oidcUser));
                userRepository.saveUserProvider(provider, subject, ret.getId());

                return ret;
            });
        
        SecurityUser securityUser = new SecurityUser(appUser, oidcUser);

        return securityUser;
    }

    private AppUser registerUser(OidcUser oidcUser) {
        AppUser appUser = new AppUser(
            "ID",
            oidcUser.getEmail(),
            oidcUser.getGivenName(),
            oidcUser.getFamilyName(),
            oidcUser.getFullName(),
            oidcUser.getPicture()
        );

        userRepository.saveUser(appUser);

        return appUser;
    }
}
