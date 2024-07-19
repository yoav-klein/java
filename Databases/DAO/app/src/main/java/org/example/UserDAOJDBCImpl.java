package org.example;


import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOJDBCImpl implements UserDAO {

    private String dbUrl;
    private String user;
    private String password;

    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String ADD_USER = "INSERT INTO users VALUES(?, ?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";


    public UserDAOJDBCImpl(String dbUrl, String user, String password) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch(SQLException e) {
            System.out.println("Error creating connection with DB");
            System.out.println(e);
        }
        return connection;
    }

    private User getUserFromResultSet(ResultSet rs) {
        User user = null;
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
    
            user = new User(id, name);
            
        } catch(SQLException e) {
            System.out.println("Error reading user data");
            System.out.println(e);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try(Connection conn = getConnection();
            PreparedStatement prpst = conn.prepareStatement(GET_ALL_USERS)) {
            
            ResultSet rs = prpst.executeQuery();
            while(rs.next()) {
                User user = getUserFromResultSet(rs);
                users.add(user);
            }  

        } catch(SQLException e) {
            System.out.println("Error getting all users");
            System.out.println(e);
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        try(Connection conn = getConnection();
            PreparedStatement prpst = conn.prepareStatement(ADD_USER)) {
        
            prpst.setInt(1, user.getId());
            prpst.setString(2, user.getName());

            prpst.executeUpdate();

        } catch(SQLException e) {
            System.out.println("Error adding user");
            System.out.println(e);
        }
        
    }

    @Override
    public void deleteUser(User user) {
        try(Connection conn = getConnection();
            PreparedStatement prpst = conn.prepareStatement(DELETE_USER)) {

            prpst.setInt(1, user.getId());
            prpst.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error deleting user");
            System.out.println(e);
        }
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        try(Connection conn = getConnection();
            PreparedStatement prpst = conn.prepareStatement(GET_USER_BY_ID)) {
            
            prpst.setInt(1, id);
            ResultSet rs = prpst.executeQuery();
            if(rs.next()) {
                user = getUserFromResultSet(rs);
            }
        
        } catch(SQLException e) {
            System.out.println("Error getting user");
            System.out.println(e);
        }

        return user;
    }
    
}
