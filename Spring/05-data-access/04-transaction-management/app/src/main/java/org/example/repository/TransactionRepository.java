package org.example.repository;

import java.util.List;

import javax.sql.DataSource;

import org.example.model.Account;
import org.example.model.Transaction;
import org.example.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_ALL_ACCOUNTS = "SELECT accounts.sum, users.id, users.name FROM accounts JOIN users ON accounts.owner_id = users.id";
    private static final String GET_ALL_TRANSACTIONS = "SELECT transactions.sum, transactions.sender_id, sender.name AS sender_name, transactions.receiver_id, receiver.name AS receiver_name FROM transactions JOIN users sender ON transactions.sender_id = sender.id JOIN users receiver ON transactions.receiver_id = receiver.id";
    private static final String GET_ACCOUNT_BY_ID = "SELECT accounts.sum, users.id, users.name FROM accounts JOIN users ON accounts.owner_id = users.id WHERE accounts.owner_id = ?";
    private static final String PULL_FROM_ACCOUNT = "UPDATE accounts SET sum = sum - ? WHERE owner_id = ?";
    private static final String PUT_IN_ACCOUNT = "UPDATE accounts SET sum = sum + ? WHERE owner_id = ?";
    private static final String WRITE_TRANSACTION = "INSERT INTO transactions(sender_id, receiver_id, sum) VALUES(?, ?, ?)";

    
    private final RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        
        return user;
    };
    
    private final RowMapper<Account> accountRowMapper = (resultSet, rowNum) -> {
        Account account = new Account();
        account.setSum(resultSet.getInt("accounts.sum"));
        
        User user = new User();
        user.setId(resultSet.getInt("users.id"));
        user.setName(resultSet.getString("users.name"));
        account.setOwner(user);
        
        return account;
    };

    private final RowMapper<Transaction> transactionRowMapper = (resultSet, rowNum) -> {
        Transaction transaction = new Transaction();

        transaction.setSum(resultSet.getInt("sum"));
        
        User sender = new User();
        sender.setId(resultSet.getInt("transactions.sender_id"));
        sender.setName(resultSet.getString("sender_name"));
        transaction.setSender(sender);

        User receiver = new User();
        receiver.setId(resultSet.getInt("transactions.receiver_id"));
        receiver.setName(resultSet.getString("receiver_name"));
        transaction.setReceiver(receiver);
        
        return transaction;
    };
    
    private JdbcTemplate jdbcTemplate;
    
    public TransactionRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<User> getAllUsers() {
        return jdbcTemplate.query(GET_ALL_USERS, userRowMapper);
    }

    public List<Account> getAllAccounts() {
        return jdbcTemplate.query(GET_ALL_ACCOUNTS, accountRowMapper);
    }

    public Account getAccountById(int id) {
        return jdbcTemplate.queryForObject(GET_ACCOUNT_BY_ID, accountRowMapper, id);
    }

    public void pull(int id, int sum) {
        jdbcTemplate.update(PULL_FROM_ACCOUNT, sum, id);
    }

    public void put(int id, int sum) {
        jdbcTemplate.update(PUT_IN_ACCOUNT, sum, id);
    }

    public void writeTransaction(int senderId, int receiverId, int sum) {
        jdbcTemplate.update(WRITE_TRANSACTION, senderId, receiverId, sum);
    }

    public List<Transaction> getAllTransactions() {
        return jdbcTemplate.query(GET_ALL_TRANSACTIONS, transactionRowMapper);
    }
}
