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
import  static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.*;

import com.example.business.SpringBusinessConfig;
import com.example.business.model.Invitation;
import com.example.business.model.Tenant;
import com.example.business.model.User;
import com.example.business.service.UserService;
import com.example.security.SpringSecurityConfig;
import com.example.web.SpringWebConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebConfig.class, SpringSecurityConfig.class, SpringBusinessConfig.class })
public class BasicTest extends AbstractTransactionalTestNGSpringContextTests {
    // extending AbstractTransactionalTestNGSpringContextTests so that each test method will rollback database changes
    
	@Autowired
	private WebApplicationContext context;

    @Autowired
    UserService userService;

	private MockMvc mvc;
    private Tenant tenant;
/* 
    @BeforeClass
    public void createTenant() throws Exception {
        mvc = webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
        
        // create tenant
        mvc.perform(post("/tenants").param("name", "HomeSweetHome").with(john()).with(csrf()));
        // assert tenant was created
        MvcResult result = mvc.perform(get("/my-tenants").with(john())).andReturn();
        Map<String, Object> model = result.getModelAndView().getModel();
        List<Tenant> listOfTenants = (List<Tenant>)model.get("tenants");
        this.tenant = listOfTenants.get(0);
    }

    @AfterClass
    public void deleteTenant() throws Exception {
        mvc.perform(delete(String.format("/tenants/%s", this.tenant.getId())));
    } */

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
    
	
}