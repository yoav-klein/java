package com.example.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.security.model.SecurityUser;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String sayHello(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        model.addAttribute("user", securityUser.getAppUser());
        return "index"; // This corresponds to the view name
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
   
}