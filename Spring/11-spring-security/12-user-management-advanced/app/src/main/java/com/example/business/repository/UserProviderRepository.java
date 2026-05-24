package com.example.business.repository;

import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;


@Repository
public class UserProviderRepository {
    /* private JdbcTemplate jdbcTemplate;

    public UserProviderRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveUserProvider(String userId, String provider, String providerSubject) {
        this.jdbcTemplate.update("INSERT INTO user_provider(user_id, provider, provider_subject) VALUES(?, ?, ?)", userId, provider, providerSubject);
    }

    public Optional<User> findUserByProviderDetails(String provider, String providerSubject) {
        try {
            User user = this.jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = (SELECT user_id FROM user_provider WHERE provider = ? AND provider_subject = ?)", UserRepository.userRowMapper, provider, providerSubject);
            return Optional.of(user);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    } */
}