package com.example.business.repository;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import com.example.business.model.Product;

@Repository
public class ProductRepository {
    private static final String GET_ALL_PRODUCTS_TEMPLATE = "SELECT * FROM %s.product";
    
    private JdbcTemplate jdbcTemplate;

    private RowMapper productMapper = (rs, rowNum) -> {
        Product p = new Product(rs.getInt("id"), rs.getString("name"));

        return p;
    };

    public ProductRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Product> getAllProducts(String tenantId) {
        return this.jdbcTemplate.query(String.format(GET_ALL_PRODUCTS_TEMPLATE, tenantId), productMapper);
    }
}
