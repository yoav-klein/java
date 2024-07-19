package org.example;

import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers();
    public void addUser(User user);
    public void deleteUser(User user);
    public User getUserById(int id);
}
