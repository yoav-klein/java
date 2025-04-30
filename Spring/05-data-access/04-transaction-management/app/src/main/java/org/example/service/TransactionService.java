package org.example.service;

import java.util.List;

import org.example.model.Account;
import org.example.model.Transaction;
import org.example.model.User;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository transactionRepository;
    
    public List<User> getAllUsers() {
        return transactionRepository.getAllUsers();
    }

    public List<Account> getAllAccounts() {
        return transactionRepository.getAllAccounts();
    }

    // the transfer method must be transactional, since all its operations must be 
    // either all completed or netiher

    @Transactional
    public void transfer(int senderId, int receiverId, int sum, boolean shouldThrow) throws Exception{
        Account sender = transactionRepository.getAccountById(senderId);
        if(sender.getSum() < sum) {
            System.out.println("You don't have enough money");
            return;
        }
        
        transactionRepository.pull(senderId, sum);

        // a RuntimeException triggers a rollback
        if(shouldThrow) throw new RuntimeException("Stuck in the middle");
        transactionRepository.put(receiverId, sum);
        transactionRepository.writeTransaction(senderId, receiverId, sum);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }
}
