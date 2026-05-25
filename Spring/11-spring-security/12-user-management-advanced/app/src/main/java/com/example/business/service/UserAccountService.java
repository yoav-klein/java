package com.example.business.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.business.model.AppUser;
import com.example.business.model.UserProviderLink;
import com.example.business.repository.UserProviderRepository;
import com.example.business.repository.UserRepository;

@Service
public class UserAccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProviderRepository userProviderRepository;

    @Transactional
    public AppUser resolveOidcUser(String provider, OidcUser oidcUser) {
        
        // check if user is in the user_provider table, if so load him from the users table
        // create a AppUser instance
        
        // if not exist, check if email exists
            // if exists - link accounts (add to user_provider)
            // else - register user
        AppUser appUser = userRepository.findByProviderAndSubject(provider, oidcUser.getSubject())
            .orElseGet(() -> {
                AppUser ret = userRepository.findByEmail(oidcUser.getEmail()).orElseGet(() -> registerUser(oidcUser));
                userProviderRepository.save(new UserProviderLink(ret.getId(), provider, oidcUser.getSubject()));

                return ret;
            });
        
        return appUser;
    }

    
    private AppUser registerUser(OidcUser oidcUser) {
        AppUser appUser = new AppUser(
            UUID.randomUUID().toString(),
            oidcUser.getEmail(),
            oidcUser.getGivenName(),
            oidcUser.getFamilyName(),
            oidcUser.getFullName(),
            oidcUser.getPicture()
        );

        userRepository.save(appUser);

        return appUser;
    }
}
