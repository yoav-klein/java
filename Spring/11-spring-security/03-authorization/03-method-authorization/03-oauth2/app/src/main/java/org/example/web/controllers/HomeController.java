package org.example.web.controllers;

import java.util.Optional;

import org.example.business.model.Account;
import org.example.business.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {
    @Autowired
    AccountService accountService;

    @RequestMapping("/")
    public String home(Model model, @AuthenticationPrincipal Object user) {
        OAuth2User oauth2user = (OAuth2User)user;
        model.addAttribute("id", oauth2user.getAttribute("sub"));
        model.addAttribute("name", oauth2user.getAttribute("name"));
        
        return "index";
    }

    @RequestMapping("/accounts/{id}")
    public String myAccount(Model model, @AuthenticationPrincipal Object user, @PathVariable("id") String id) {
        Optional<Account> accountOptional = accountService.getAccountById(id);
        
        model.addAttribute("account", accountOptional.get());
        
        return "my-account";
    }

    @PostMapping("/accounts/{id}/push")
    public String pushToAccount(Model model, @AuthenticationPrincipal Object user, @RequestParam("sum") int sum, @PathVariable("id") String id) {
        Optional<Account> accountOptional = accountService.getAccountById(id);
        model.addAttribute("account", accountOptional.get());

        accountService.pushToAccount(id, sum);
        
        return "my-account";
    }

    @PostMapping("/accounts/{id}/pull")
    public String pullToAccount(Model model, @AuthenticationPrincipal Object user, @RequestParam("sum") int sum, @PathVariable("id") String id) {
        Optional<Account> accountOptional = accountService.getAccountById(id);
        model.addAttribute("account", accountOptional.get());

        accountService.pullFromAccount(id, sum);
        
        return "my-account";
    }
   
}