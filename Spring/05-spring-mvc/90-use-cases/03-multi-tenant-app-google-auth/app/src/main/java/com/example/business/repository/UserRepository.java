
package com.example.business.repository;

import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.business.model.User;

@Repository
public class UserRepository {

    private final String GET_USER_BY_EMAIL = "SELECT * FROM tenant_system.users WHERE email = ?";
    private final String GET_USER_BY_ID = "SELECT * FROM tenant_system.users WHERE id = ?";
    private final String ADD_USER = "INSERT INTO tenant_system.users VALUES(?, ?, ?)";

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));

        return user;
    };

    
    public Optional<User> findByEmail(String email) {
        User user;
        try {
            user = this.jdbcTemplate.queryForObject(GET_USER_BY_EMAIL, userRowMapper, email);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public Optional<User> findById(String id) {
        User user;
        try {
            user = this.jdbcTemplate.queryForObject(GET_USER_BY_ID, userRowMapper, id);
        } catch(EmptyResultDataAccessException e) {
            System.out.println("UserRepository:: user with id " + id + " is not found!");
            return Optional.empty();
        }
        System.out.println("UserRepository:: user with id " + id + " is found!");
        return Optional.of(user);
        
    }

    public void addUser(User user) {
        System.out.println("Adding user: " + user.getId() + "name: " + user.getName() + " email: " + user.getEmail());
        this.jdbcTemplate.update(ADD_USER, user.getId(), user.getName(), user.getEmail());
    }
}