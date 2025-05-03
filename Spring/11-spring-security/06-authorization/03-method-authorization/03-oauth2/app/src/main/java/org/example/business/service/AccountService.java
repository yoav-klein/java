package org.example.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.business.model.Account;
import org.example.business.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private List<Account> accounts = new ArrayList<>();
    
    // the #root is a concept in SpEL, and it refers to the root context object.
    // See https://docs.spring.io/spring-framework/reference/core/expressions/language-ref/variables.html
    
    public void createAccount(String id, String name) {
        System.out.println("Creating account for: " + id);
        this.accounts.add(new Account(0, new User(id, name)));
    }

    // we can authorize with only SpEL by accessing the principal.claims['sub'] and compare it to the 
    // requested ID
    @PreAuthorize("principal.claims['sub'] == #id")
    public Optional<Account> getAccountById(@P("id") String userId) {
        for(Account account : this.accounts) {
            if(account.getOwner().getId().equals(userId)) return Optional.of(account);
        }
        return Optional.empty();
    }

    // and we can do it programatically using the AuthBean bean's isUserAccount method
    @PreAuthorize("@authz.isUsersAccount(authentication, #id, #root)")
    public void pullFromAccount(@P("id") String userId, int sum) {
        for(Account account : this.accounts) {
            if(account.getOwner().getId().equals(userId)) account.pull(sum);
        }
    }

    @PreAuthorize("@authz.isUsersAccount(authentication, #id, #root)")
    public void pushToAccount(@P("id") String userId, int sum) {
        for(Account account : this.accounts) {
            if(account.getOwner().getId().equals(userId)) account.push(sum);
        }
    }
}
