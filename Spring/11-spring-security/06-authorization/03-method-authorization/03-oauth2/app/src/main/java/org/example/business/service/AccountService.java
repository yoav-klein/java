package org.example.business.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.security.core.parameters.P;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import org.example.business.model.Account;
import org.example.business.model.User;

@Service
public class AccountService {

    private List<Account> accounts = new ArrayList<>();
    
    // the #root is a concept in SpEL, and it refers to the root context object.
    // See https://docs.spring.io/spring-framework/reference/core/expressions/language-ref/variables.html
    
    public void addAccount(User user) {
        this.accounts.add(new Account(0, user));
    }

    @PreAuthorize("@authz.isUsersAccount(authentication, #id, #root)")
    public Optional<Account> getAccountById(@P("id") String userId) {
        for(Account account : this.accounts) {
            if(account.getOwner().getId() == userId) return Optional.of(account);
        }
        return Optional.empty();
    }

    @PreAuthorize("@authz.isUsersAccount(authentication, #id, #root)")
    public void pullFromAccount(@P("id") String userId, int sum) {
        for(Account account : this.accounts) {
            if(account.getOwner().getId() == userId) account.pull(sum);
        }
    }

    @PreAuthorize("@authz.isUsersAccount(authentication, #id, #root)")
    public void pushToAccount(@P("id") String userId, int sum) {
        for(Account account : this.accounts) {
            if(account.getOwner().getId() == userId) account.push(sum);
        }
    }
/* 
    @PreAuthorize("@authz.decideBefore(authentication, #root)")
    @PostAuthorize("@authz.decideAfter(authentication, #root)")
    public String someImportantMethod() {
        System.out.println("Important method");
        return "kuku";
    } */
}
