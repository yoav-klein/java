package com.example;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import  org.springframework.test.web.servlet.MvcResult;
import  static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.business.SpringBusinessConfig;
import com.example.business.model.Tenant;
import com.example.business.model.User;
import com.example.business.service.UserService;
import com.example.security.SpringSecurityConfig;
import com.example.web.SpringWebConfig;

/**
 * A base TestNG class that creates a tenant in the beginning and destroys it in the end
 */

@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebConfig.class, SpringSecurityConfig.class, SpringBusinessConfig.class })
public class TenantBase extends AbstractTransactionalTestNGSpringContextTests {
    // extending AbstractTransactionalTestNGSpringContextTests so that each test method will rollback database changes
    
	@Autowired
	private WebApplicationContext context;

    @Autowired
    UserService userService;

	protected MockMvc mvc;
    protected String tenantId;

    @BeforeClass
    public void createTenant() throws Exception {
        mvc = webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
        
        // create tenant
        MvcResult result = mvc.perform(post("/tenants").param("name", "HomeSweetHome").with(yoav()).with(csrf())).andReturn();
        this.tenantId = (String)result.getFlashMap().get("tenantId");

    }
    
    @AfterClass
    public void deleteTenant() throws Exception {
        mvc.perform(delete(String.format("/tenants/%s", this.tenantId)).with(yoav()).with(csrf()));
    }

    // AuthenticationSuccessHandler are not called in Spring Tests, 
    // so we need to manually register the user
    protected RequestPostProcessor yoav() {
        User user = new User("yoav", "Yoav Klein", "yoav.klein@tmail.com", "https://yoav.picture.com");
        // check if the user already exists in the database
        if(!userService.checkIfUserExists(user.getId())) {
            userService.addUser(user);
        }
        
        return oauth2Login().attributes(attrs -> {
            attrs.put("sub", user.getId());
            attrs.put("name", user.getName());
            attrs.put("email", user.getEmail());
            attrs.put("pictureUrl", user.getPictureUrl());
        });
    }

    // AuthenticationSuccessHandler are not called in Spring Tests, 
    // so we need to manually register the user
    protected RequestPostProcessor john() {
        User user = new User("john", "John Adams", "john.adams@tmail.com", "https://john.picture.com");
        // check if the user already exists in the database
        if(!userService.checkIfUserExists(user.getId())) {
            userService.addUser(user);
        }
        
        return oauth2Login().attributes(attrs -> {
            attrs.put("sub", user.getId());
            attrs.put("name", user.getName());
            attrs.put("email", user.getEmail());
            attrs.put("pictureUrl", user.getPictureUrl());
        });
    }

    protected RequestPostProcessor bob() {
        User user = new User("bob", "Bob Ali", "bob.ali@tmail.com", "https://bob.picture.com");
        // check if the user already exists in the database
        if(!userService.checkIfUserExists(user.getId())) {
            userService.addUser(user);
        }
        
        return oauth2Login().attributes(attrs -> {
            attrs.put("sub", user.getId());
            attrs.put("name", user.getName());
            attrs.put("email", user.getEmail());
            attrs.put("pictureUrl", user.getPictureUrl());
        });
    }

    
	
}