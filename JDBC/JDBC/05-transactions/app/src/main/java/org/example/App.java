/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class App {

    public static DataSource createDataSource() {
        String dbUrl = "jdbc:mysql://localhost:3306/users";
        String dbUser = "yoav";
        String dbPassword = "yoav";

        MysqlDataSource mysqlDS = null;
        
        mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(dbUrl);
        mysqlDS.setUser(dbUser);
        mysqlDS.setPassword(dbPassword);

        return mysqlDS;
        
    }

    public static void rollbackExample() {
        try(Connection conn = createDataSource().getConnection()) {

            // in order to use transactions, we must set auto-commit to false
            conn.setAutoCommit(false);

            // we're gonna change the name of user with id 1 to some other name
            try(PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET name = ? WHERE id = 1")) {
                pstmt.setString(1, "yovav");
                int count = pstmt.executeUpdate();
                System.out.println("changed: " + count);
            }
            
            // we didn't commit yet. But let's see the value currently, you'll see that it's the updated value
            try(Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = 1");
                while(rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            }

            try {
                Thread.sleep(10000);
            } catch(Exception e) {}

            // let's do a rollback to rollback the change
            System.out.println("Rolling back");
            conn.rollback();

            // and see that we have the previous value
            try(Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = 1");
                while(rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            }
            
        } catch(SQLException e) {
            System.out.println("SQL Exception!");
            System.out.println(e);
        }
    }

    public static void commitExample() {
        try(Connection conn = createDataSource().getConnection()) {

            // in order to use transactions, we must set auto-commit to false
            conn.setAutoCommit(false);

            // we're gonna change the name of user with id 1 to some other name
            try(PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET name = ? WHERE id = 1")) {
                pstmt.setString(1, "bonbon");
                int count = pstmt.executeUpdate();
                System.out.println("changed: " + count);
            }

            conn.commit();
            
        } catch(SQLException e) {
            System.out.println("SQL Exception!");
            System.out.println(e);
        }
    }

    public static void main(String[] args)  {
        commitExample();
        rollbackExample();

    }
}
