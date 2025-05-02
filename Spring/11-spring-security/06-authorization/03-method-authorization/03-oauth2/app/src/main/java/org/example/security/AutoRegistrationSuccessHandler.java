package org.example.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutoRegistrationSuccessHandler implements AuthenticationSuccessHandler {

    // @Autowired
    // AccountService accountService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = token.getPrincipal();

        Map<String, Object> attrs = oauth2User.getAttributes();
        // String email = (String) attrs.get("email");
        String name = (String) attrs.get("name");
        String sub = (String) attrs.get("sub");

        // accountService.createAccount(sub, name);

        response.sendRedirect("/app"); // Redirect to home page after successful registration
    }
}