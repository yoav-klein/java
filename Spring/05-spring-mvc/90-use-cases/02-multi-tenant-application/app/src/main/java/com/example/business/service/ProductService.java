package com.example.business.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.business.model.Product;
import com.example.business.repository.ProductRepository;

public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
}
