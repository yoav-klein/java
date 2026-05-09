package com.example.business.repository;

import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

import io.micrometer.common.lang.Nullable;

import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class UserRepository {
    
    private JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveUser(String id, String displayName, String email, @Nullable String pictureUrl) {
        jdbcTemplate.update("INSERT INTO users(id, display_name, email, picture_url) VALUES(?, ?, ?, ?)", id, displayName, email, pictureUrl); 
    }

}
