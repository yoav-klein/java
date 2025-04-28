package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired 
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertUser(User user) throws SQLException {
        this.jdbcTemplate.execute((Connection conn) -> {
            try(PreparedStatement pstmt = conn.prepareStatement("insert into users (id, name) values (?, ?)")) {
                pstmt.setInt(1, user.getId());
                pstmt.setString(2, user.getName());
                int result = pstmt.executeUpdate();
                return result;
            } 
            
        });
        
    }


}
