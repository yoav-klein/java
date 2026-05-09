package com.example;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.business.SpringBusinessConfig;
import com.example.business.repository.UserRepository;

@ContextConfiguration(classes = { SpringBusinessConfig.class })
@ActiveProfiles("test")
public class DaoTests extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser() {
        userRepository.saveUser("abc", "Yoav Klein", "yoavklein25@gmail.com", null);
    }
}
