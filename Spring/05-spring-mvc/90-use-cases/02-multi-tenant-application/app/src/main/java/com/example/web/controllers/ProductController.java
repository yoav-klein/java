package com.example.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/all") 
    public String getAllProducts() {
        return "all-products.html";
    }
}
