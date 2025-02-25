/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.sql.Connection;
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

    public static void main(String[] args)  {
        
        // we create the connection using the DriverManager.getConnection
        try(Connection conn = createDataSource().getConnection()) {

            // we can check if the connection is good
            System.out.println("isValid: " + conn.isValid(0));
            
            // we want to create statement in a try clause because we need to release it after using it.
            try(Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("select * from users");
                while(rs.next()) {
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2));  
                }
            }

        } catch(SQLException e) {
            System.out.println("SQL Exception!");
            System.out.println(e);
        }
    }
}
