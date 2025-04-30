package org.example.repository;

import java.util.List;

import javax.sql.DataSource;

import org.example.model.Account;
import org.example.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_ALL_ACCOUNTS = "SELECT accounts.id, users.id, users.name FROM accounts JOIN users ON accounts.owner_id = users.id";

    
    private final RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        
        return user;
    };
    
    private final RowMapper<Account> accountRowMapper = (resultSet, rowNum) -> {
        Account account = new Account();
        account.setId(resultSet.getInt("accounts.id"));
        
        User user = new User();
        user.setId(resultSet.getInt("users.id"));
        user.setName(resultSet.getString("users.name"));
        account.setOwner(user);
        
        return account;
    };
    
    private JdbcTemplate jdbcTemplate;
    
    public TransactionRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<User> getAllUsers() {
        return jdbcTemplate.query(GET_ALL_USERS, userRowMapper);
    }
}
