package com.example.business.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.business.model.Product;
import com.example.business.repository.ProductRepository;
import com.example.helpers.TenantContext;
import com.example.business.exception.NoTenantSelectedException;
import com.example.web.forms.ProductDto;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        String tenantId = TenantContext.getCurrentTenantId();

        return productRepository.getAllProducts(tenantId);
    }

    public void addProduct(ProductDto p) {
        String tenantId = TenantContext.getCurrentTenantId();

        productRepository.addProduct(tenantId, p);
    }
}
