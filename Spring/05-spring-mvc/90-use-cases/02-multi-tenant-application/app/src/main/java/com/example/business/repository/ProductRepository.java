package com.example.business.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.business.model.Product;

@Repository
public class ProductRepository {
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }
}
