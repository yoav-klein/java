package com.example;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import  org.springframework.test.web.servlet.MockMvc;
import  org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

// oauth2login, csrf, etc.
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import org.springframework.test.context.ActiveProfiles;
// get(), post(), etc
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// status(), model(), etc.
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

import com.example.business.SpringBusinessConfig;
import com.example.business.model.Tenant;
import com.example.business.model.User;
import com.example.business.service.UserService;
import com.example.security.SpringSecurityConfig;
import com.example.web.SpringWebConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebConfig.class, SpringSecurityConfig.class, SpringBusinessConfig.class })
public class SecurityTest extends AbstractTestNGSpringContextTests {
    
	@Autowired
	private WebApplicationContext context;

    @Autowired
    UserService userService;

	private MockMvc mvc;

	@BeforeMethod
	public void setup() {
		mvc = webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
	}

    // AuthenticationSuccessHandler are not called in Spring Tests, 
    // so we need to manually register the user
    public RequestPostProcessor john() {
        User user = new User("john", "John Adams", "john.adams@tmail.com", "https://john.picture.com");
        // check if the user already exists in the database
        Optional<User> optionalUser = userService.getUserById("john");
        if(optionalUser.isEmpty()) {
            userService.addUser(user);
        }
        
        return oauth2Login().attributes(attrs -> {
            attrs.put("sub", user.getId());
            attrs.put("name", user.getName());
            attrs.put("email", user.getEmail());
            attrs.put("pictureUrl", user.getPictureUrl());
        });
    }

    public RequestPostProcessor muhammad() {
        User user = new User("muhammad", "Muhammad Ali", "muhammad.ali@tmail.com", "https://muhammad.picture.com");
        // check if the user already exists in the database
        Optional<User> optionalUser = userService.getUserById("muhammad");
        if(optionalUser.isEmpty()) {
            userService.addUser(user);
        }
        
        return oauth2Login().attributes(attrs -> {
            attrs.put("sub", user.getId());
            attrs.put("name", user.getName());
            attrs.put("email", user.getEmail());
            attrs.put("pictureUrl", user.getPictureUrl());
        });
    }
    
	// basic test
	@Test
	public void testOauth2() throws Exception {
        mvc.perform(get("/").with(muhammad())).andExpect(status().isOk());

        MvcResult result = mvc.perform(get("/my-tenants").with(john()))
            .andExpect(model().attributeExists("tenants"))
            .andExpect(status().isOk())
            .andReturn();
        
        // assert empty list of tenants
        Map<String, Object> model = result.getModelAndView().getModel();
        List<Tenant> listOfTenants = (List<Tenant>)model.get("tenants");
        Assert.assertTrue(listOfTenants.isEmpty());
		
        // create tenant
        result = mvc.perform(post("/tenants").param("name", "HomeSweetHome").with(john()).with(csrf()))
            .andReturn();
        
        model = result.getModelAndView().getModel();
        listOfTenants = (List<Tenant>)model.get("tenants");
        Assert.assertTrue(listOfTenants.isEmpty());
	}
	
}