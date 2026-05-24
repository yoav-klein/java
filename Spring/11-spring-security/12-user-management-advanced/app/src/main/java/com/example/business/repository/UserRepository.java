package com.example.business.repository;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.business.model.*;

@Repository
public class UserRepository {
    
    private JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static RowMapper<AppUser> userRowMapper = (resultSet, rowNum) -> {
        AppUser user = new AppUser();
        user.setId(resultSet.getString("id"));
        user.setDisplayName(resultSet.getString("display_name"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setPictureUrl(resultSet.getString("picture_url"));

        return user;
    };

    public void saveUser(AppUser user) {
        jdbcTemplate.update("INSERT INTO users(id, display_name, first_name, last_name, email, picture_url) VALUES(?, ?, ?, ?, ?, ?)", 
            user.getId(), 
            user.getDisplayName(),
            user.getFirstName(), 
            user.getLastName(),
            user.getEmail(),
            user.getPictureUrl());
    }

    public Optional<AppUser> findByProviderAndSubject(String provider, String providerSubject) {
        try {
            AppUser user = this.jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = (SELECT user_id FROM user_provider WHERE provider = ? AND provider_subject = ?)", UserRepository.userRowMapper, provider, providerSubject);
            return Optional.of(user);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void saveUserProvider(String provider, String providerSubject, String userId) {
        jdbcTemplate.update("INSERT INTO user_provider(provider, provider_subject, user_id) VALUES(?, ?, ?)",
            provider,
            providerSubject,
            userId
        );
    }

    public Optional<AppUser> findUserById(String userId) {
        try {
            AppUser user = this.jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", userRowMapper, userId);
            return Optional.ofNullable(user);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<AppUser> findUserByEmail(String email) {
        try {
            AppUser user = this.jdbcTemplate.queryForObject("SELECT * FROM users WHERE email = ?", userRowMapper, email);
            return Optional.ofNullable(user);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<AppUser> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", userRowMapper);
    }

}
