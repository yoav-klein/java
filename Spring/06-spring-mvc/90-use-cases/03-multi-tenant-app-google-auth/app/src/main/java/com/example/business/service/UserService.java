package com.example.business.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.business.repository.UserRepository;
import com.example.business.repository.InvitationRepository;
import com.example.business.model.Invitation;
import com.example.business.model.User;
import com.example.business.exception.UserNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository for database operations

    @Autowired
    private InvitationRepository invitationRepository;

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public User getUserById(String userId) throws UserNotFoundException {
        return userRepository.findUserById(userId).orElseThrow(() -> { return new UserNotFoundException(); });
    }

    public boolean checkIfUserExists(String userId) {
        try {
            getUserById(userId);
            return true;
        } catch(UserNotFoundException e) {
            return false;
        }
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> { return new UserNotFoundException(); });
    }

    public List<Invitation> getAllInvitationsForUser(String userId) {
        return invitationRepository.getAllInvitationsForUser(userId);

    }

}