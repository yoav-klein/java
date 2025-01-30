package com.example.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.example.business.model.Product;
import com.example.business.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping({"", "/"}) 
    public String getAllProducts(Model model) {
        List<Product> allProducts = productService.getAllProducts();

        model.addAttribute("allProducts", allProducts);
        return "all-products.html";
    }
}
