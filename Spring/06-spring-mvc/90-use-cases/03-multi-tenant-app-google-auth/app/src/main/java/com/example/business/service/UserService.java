package com.example.business.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.business.repository.UserRepository;
import com.example.business.model.User;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository for database operations

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}