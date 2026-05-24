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

    public void saveUser(String id, String displayName, String firstName, String lastName, String email, String pictureUrl) {
        jdbcTemplate.update("INSERT INTO users(id, display_name, first_name, last_name, email, picture_url) VALUES(?, ?, ?, ?, ?, ?)", id, displayName, firstName, lastName, email, pictureUrl);
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
