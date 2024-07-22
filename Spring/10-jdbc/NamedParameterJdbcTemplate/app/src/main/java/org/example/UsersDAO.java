package org.example;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

@Repository
public class UsersDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired 
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource); 
    }

    int getCount() {
        int rowCount = this.namedParameterJdbcTemplate.getJdbcTemplate().queryForObject("select count(*) from users", Integer.class);
        return rowCount;
    }

    int countUsersByName(String name) {
        int countOfUsersByName = this.namedParameterJdbcTemplate.getJdbcTemplate().queryForObject("select count(*) from users where name = ?", Integer.class, name);
        return countOfUsersByName;
    }

    String getNameById(int id) {
        String sql = "select name from users where id = :id";
        SqlParameterSource namedParams = new MapSqlParameterSource("id", id);
        return this.namedParameterJdbcTemplate.queryForObject(sql, namedParams, String.class);
    }

    User getUser(int id) {
        SqlParameterSource namedParams = new MapSqlParameterSource("id", id);
        String sql = "select id, name from users where id = :id";

        User user = this.namedParameterJdbcTemplate.queryForObject(
		sql, namedParams,
		(resultSet, rowNum) -> {
			User u = new User();
			u.setId(resultSet.getInt("id"));
			u.setName(resultSet.getString("name"));
			return u;
		});

        return user;
    }

    List<User> getAllUsers() {
        List<User> users = this.namedParameterJdbcTemplate.getJdbcTemplate().query(
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
        SqlParameterSource namedParams = new BeanPropertySqlParameterSource(user);

        this.namedParameterJdbcTemplate.update(
		"insert into users (id, name) values (:id, :name)",
		namedParams);
    }

    void updateName(int id, String name) {
        Map<String, Object> values = new HashMap<>();
        values.put("id", id);
        values.put("name", name);
        
        
        SqlParameterSource namedParams = new MapSqlParameterSource(values);

        this.namedParameterJdbcTemplate.update(
		"update users set name = :name where id = :id",
		namedParams);
    }

    void deleteUser(int id) {
        this.namedParameterJdbcTemplate.getJdbcTemplate().update("delete from users where id = ?", id);
    }
    
    

}
