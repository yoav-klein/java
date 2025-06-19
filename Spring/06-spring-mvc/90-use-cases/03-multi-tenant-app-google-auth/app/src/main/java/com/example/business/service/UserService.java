package com.example.business.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.business.repository.UserRepository;
import com.example.business.repository.InvitationRepository;
import com.example.business.model.Invitation;
import com.example.business.model.User;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository for database operations

    @Autowired
    private InvitationRepository invitationRepository;

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.getUserById(userId);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<Invitation> getAllInvitationsForUser(String userId) {
        return invitationRepository.getAllInvitationsForUser(userId);

    }

}