package com.example.daotests;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.business.model.AppUser;
import com.example.business.model.UserProviderLink;
import com.example.business.repository.UserRepository;
import com.example.business.repository.UserProviderRepository;
import com.example.test.BusinessTestBase;

public class DaoTests extends BusinessTestBase {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProviderRepository userProviderRepository;

    private void saveUser(String id, String email) {
        AppUser user = new AppUser(id, email, "Yoav", "Klein", null);

        userRepository.save(user);
    }

    @Test
    public void testSaveUser() {
        int initialCount = countRowsInTable("users");
        String id = "abc";
        saveUser(id, "batata@gmail.com");
        Assert.assertEquals(initialCount + 1, countRowsInTable("users"));

        Optional<AppUser> yoav = userRepository.findById(id);
        Assert.assertEquals(yoav.isPresent(), true);
    }

    @Test
    public void testFindUserById_empty() {
        Optional<AppUser> user = userRepository.findById("batata");

        Assert.assertEquals(false, user.isPresent());
    }

    @Test
    public void testGetAllUsers() {
        saveUser("Yoav", "batata@gmail.com");
        saveUser("Dikla", "dikla@gmail.com");
        saveUser("Tamar", "tamar@gmail.com");
        Assert.assertEquals(userRepository.getAllUsers().size(), 3);
    }

    @Test
    public void testFindUserByEmail() {
        saveUser("Yoav", "batata@gmail.com");
        Assert.assertEquals(userRepository.findByEmail("batata@gmail.com").isPresent(), true);
    }

    @Test
    public void testSaveUserProvider() {
        saveUser("yoav", "batata@gmail.com");
        userProviderRepository.save(new UserProviderLink("yoav", "google", "abc123"));
        Assert.assertEquals(userRepository.findByProviderAndSubject("google", "abc123").isPresent(), true);
    }

    @Test
    public void testFindByUserProvider() {
        Assert.assertEquals(userRepository.findByProviderAndSubject("google", "1233").isPresent(), false);
    }
}
