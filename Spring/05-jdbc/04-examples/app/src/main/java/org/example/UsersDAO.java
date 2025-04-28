package org.example;

import javax.sql.DataSource;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class UsersDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired 
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource); 
    }

    int getCount() {
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
        return rowCount;
    }

    int countUsersByName(String name) {
        int countOfUsersByName = this.jdbcTemplate.queryForObject("select count(*) from users where name = ?", Integer.class, name);
        return countOfUsersByName;
    }

    String getNameById(int id) {
        String name = this.jdbcTemplate.queryForObject(
            "select name from users where id = ?",
            String.class, id);
        
        return name;
    }

    User getUser(int id) {
        User user = jdbcTemplate.queryForObject(
		"select id, name from users where id = ?",
		(resultSet, rowNum) -> {
			User u = new User();
			u.setId(resultSet.getInt("id"));
			u.setName(resultSet.getString("name"));
			return u;
		},
		id);

        return user;
    }

    List<User> getAllUsers() {
        List<User> users = this.jdbcTemplate.query(
		"select id, name from users",
		(resultSet, rowNum) -> {
			User user = new User();
			user.setId(resultSet.getInt("id"));
			user.setName(resultSet.getString("name"));
			return user;
		});
        return users;
    }

    void insertUser(User user) {
        this.jdbcTemplate.update(
		"insert into users (id, name) values (?, ?)",
		user.getId(), user.getName());
    }

    void updateName(int id, String name) {
        this.jdbcTemplate.update(
		"update users set name = ? where id = ?",
		name, id);
    }

    void deleteUser(int id) {
        this.jdbcTemplate.update("delete from users where id = ?", id);
    }
    
    

}
