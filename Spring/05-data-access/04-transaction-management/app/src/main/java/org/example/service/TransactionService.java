package org.example.service;

import java.util.List;

import org.example.model.User;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository transactionRepository;
    
    public List<User> getAllUsers() {
        return transactionRepository.getAllUsers();
    }
}
