package com.example.business.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.business.model.Product;
import com.example.web.forms.ProductDto;

@Repository
public class ProductRepository {
    private static final String GET_ALL_PRODUCTS_TEMPLATE = "SELECT * FROM tenant_%s.product";
    private static final String ADD_PRODUCT = "INSERT INTO tenant_%s.product(name) VALUES(?)";
    
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

    public void addProduct(String tenantId, ProductDto p) {
        this.jdbcTemplate.update(String.format(ADD_PRODUCT, tenantId), p.getName());
    } 
}
