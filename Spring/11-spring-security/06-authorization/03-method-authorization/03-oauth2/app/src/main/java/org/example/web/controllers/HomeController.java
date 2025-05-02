package org.example.web.controllers;

import java.util.Optional;

import org.example.business.model.Account;
import org.example.business.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



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
    public String myAccount(Model model, @AuthenticationPrincipal Object user) {
        OAuth2User oauth2user = (OAuth2User)user;
        Optional<Account> accountOptional = accountService.getAccountById(oauth2user.getAttribute("sub"));
        
        model.addAttribute("account", accountOptional.get());
        
        return "my-account";
    }
   
}