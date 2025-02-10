package com.example.security;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;


public class GoogleAuthenticationProvider implements AuthenticationProvider {

    private final String WEB_CLIENT_ID;

    public GoogleAuthenticationProvider(String webClientId) { 
        this.WEB_CLIENT_ID = webClientId;
        
    }

    @Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("In AuthenticationProvider");
        
        
        GoogleAuthenticationIdToken token = (GoogleAuthenticationIdToken)authentication;
        
        String idTokenString = token.getIdToken();
        System.out.println(idTokenString);
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
            .setAudience(Collections.singletonList(WEB_CLIENT_ID))
            .build();

        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(idTokenString);
        } catch (Exception e) {
            throw new BadCredentialsException("Bad ID token");
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            GoogleIdentityDetails details = new GoogleIdentityDetails(email, name, pictureUrl, locale, familyName, givenName);

            GoogleAuthentication googleAuthentication = new GoogleAuthentication(details, idTokenString);

            System.out.println("in AuthenticationProvider, returning");
            return googleAuthentication;

        } else {
            System.out.println("BAD TOKEN");
            throw new BadCredentialsException("Bad ID token");
        }

    }

    @Override
	public boolean supports(Class<?> authentication) {
		return GoogleAuthenticationIdToken.class.isAssignableFrom(authentication);
	}

}
