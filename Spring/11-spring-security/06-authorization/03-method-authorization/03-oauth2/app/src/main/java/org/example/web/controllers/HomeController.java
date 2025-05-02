package org.example.web.controllers;

import java.util.Optional;

import org.example.business.model.Account;
import org.example.business.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Controller
public class HomeController {
    @Autowired
    AccountService accountService;

    @RequestMapping("/")
    public String sayHello(Model model, @AuthenticationPrincipal Object user) {
        OAuth2User oauth2user = (OAuth2User)user;
        Optional<Account> account = accountService.getAccountById(oauth2user.getAttribute("sub"));
        
        model.addAttribute("account", account);
        
        
        return "index"; // This corresponds to the view name
    }
   
}