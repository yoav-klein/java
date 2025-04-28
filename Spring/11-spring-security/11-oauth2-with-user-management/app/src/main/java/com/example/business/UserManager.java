package com.example.business;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserManager {
    List<GoogleUser> users = new ArrayList<>();
    
    public void addUser(String googleId, String name, String email) {
        users.add(new GoogleUser(googleId, name, email));
    }

    public GoogleUser getGoogleUser(String userId) {
        for (GoogleUser user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null; // or throw an exception if user not found
    }

    public void displayUsers() {
        for (GoogleUser user : users) {
            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
        }
    }
}