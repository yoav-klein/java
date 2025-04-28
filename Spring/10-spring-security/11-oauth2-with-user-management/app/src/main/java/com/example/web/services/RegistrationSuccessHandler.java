package com.example.web.services;

import java.io.IOException;
import java.util.Map;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.business.UserManager;
import com.example.business.GoogleUser;


public class RegistrationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        UserManager userManager = new UserManager();
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oidcUser = token.getPrincipal();

        Map<String, Object> claims = oidcUser.getAttributes();
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
        response.sendRedirect("/app"); // Redirect to home page after successful registration
    }
}