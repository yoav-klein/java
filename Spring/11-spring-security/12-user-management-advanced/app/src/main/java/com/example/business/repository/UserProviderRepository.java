package com.example.business.repository;

import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.business.model.UserProviderLink;

@Repository
public class UserProviderRepository {
    private JdbcTemplate jdbcTemplate;

    public UserProviderRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(UserProviderLink link) {
        this.jdbcTemplate.update("INSERT INTO user_provider(user_id, provider, provider_subject) VALUES(?, ?, ?)", 
            link.getUserId(), 
            link.getProvider(), 
            link.getProviderSubject());
    }
}