package com.example.web.services;

import java.util.Map;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.stereotype.Service;

import com.example.business.UserManager;
import com.example.business.GoogleUser;

@Service
public class DatabaseOidcUserService extends OidcUserService {

    private final UserManager userManager = new UserManager();

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = super.loadUser(userRequest);

        Map<String, Object> claims = oidcUser.getClaims();
        String email = (String) claims.get("email");
        String name = (String) claims.get("name");
        String sub = (String) claims.get("sub");
        // and so on and so forth

        // see if the user already exists in the database
        // if not, add them to the database
        GoogleUser user = userManager.getGoogleUser(sub);
        if (user == null) {
            userManager.addUser(sub, name, email);
        }

        // if they do exist, update their information
        //else {
        //    user.setName(name);
        //    user.setEmail(email);
        //}
        System.out.println("ALL USERS IN DATABASE: ");
        userManager.displayUsers();
        return oidcUser;
    }
}