/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UsersDAO dao = (UsersDAO)context.getBean("usersDAO");

        System.out.println(dao.getCount());

        System.out.println(dao.countUsersByName("Yoav"));
        System.out.println(dao.getNameById(1));

        User yoav = dao.getUser(1);
        System.out.println(yoav.getName());
    
        List<User> users = dao.getAllUsers();

        for(User user : users) {
            System.out.println("ID: " + user.getId() + " Name: " + user.getName());
        }

        User john = new User(5, "John");
        dao.insertUser(john);

        dao.updateName(5, "Bob");

        dao.deleteUser(5);

    }
}