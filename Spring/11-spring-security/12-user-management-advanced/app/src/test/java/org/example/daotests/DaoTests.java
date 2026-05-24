package com.example.daotests;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.business.model.AppUser;
import com.example.business.repository.UserRepository;
import com.example.test.BusinessTestBase;

public class DaoTests extends BusinessTestBase {
    
    @Autowired
    private UserRepository userRepository;

    private void saveUser(String id, String email) {
        userRepository.saveUser(id, "Yoav Klein", "Yoav", "Klein", email, null);
    }

    @Test
    public void testSaveUser() {
        int initialCount = countRowsInTable("users");
        String id = "abc";
        saveUser(id, "yoavklein25@gmail.com");
        Assert.assertEquals(initialCount + 1, countRowsInTable("users"));

        Optional<AppUser> yoav = userRepository.findUserById(id);
        Assert.assertEquals(yoav.isPresent(), true);
    }

    @Test
    public void testFindUserById_empty() {
        Optional<AppUser> user = userRepository.findUserById("batata");

        Assert.assertEquals(false, user.isPresent());
    }

    @Test
    public void testGetAllUsers() {
        saveUser("Yoav", "yoavklein25@gmail.com");
        saveUser("Dikla", "dikla@gmail.com");
        saveUser("Tamar", "tamar@gmail.com");
        Assert.assertEquals(userRepository.getAllUsers().size(), 3);
    }

    @Test
    public void testFindUserByEmail() {
        saveUser("Yoav", "yoavklein25@gmail.com");
        Assert.assertEquals(true, userRepository.findUserByEmail("yoavklein25@gmail.com").isPresent());
    }
}
