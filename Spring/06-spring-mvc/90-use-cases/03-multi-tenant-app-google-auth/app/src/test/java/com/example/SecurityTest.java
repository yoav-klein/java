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
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.business.SpringBusinessConfig;
import com.example.business.model.Invitation;
import com.example.business.model.Tenant;
import com.example.business.model.User;
import com.example.business.service.UserService;
import com.example.security.SpringSecurityConfig;
import com.example.web.SpringWebConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebConfig.class, SpringSecurityConfig.class, SpringBusinessConfig.class })
public class SecurityTest extends AbstractTransactionalTestNGSpringContextTests {
    // extending AbstractTransactionalTestNGSpringContextTests so that each test method will rollback database changes
    
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

    public RequestPostProcessor bob() {
        User user = new User("bob", "bob Ali", "bob.ali@tmail.com", "https://bob.picture.com");
        // check if the user already exists in the database
        Optional<User> optionalUser = userService.getUserById("bob");
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

    public RequestPostProcessor dana() {
        User user = new User("dana", "Dana Sela", "dana.sela@tmail.com", "https://dana.picture.com");
        // check if the user already exists in the database
        Optional<User> optionalUser = userService.getUserById("dana");
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
	public void basicTest() throws Exception {
        mvc.perform(get("/").with(bob())).andExpect(status().isOk());

        MvcResult result = mvc.perform(get("/my-tenants").with(john()))
            .andExpect(model().attributeExists("tenants"))
            .andExpect(status().isOk())
            .andReturn();
        
        // assert empty list of tenants
        Map<String, Object> model = result.getModelAndView().getModel();
        List<Tenant> listOfTenants = (List<Tenant>)model.get("tenants");
        Assert.assertTrue(listOfTenants.isEmpty());
		
        // create tenant
        mvc.perform(post("/tenants").param("name", "HomeSweetHome").with(john()).with(csrf()));
        
        // assert tenant was created
        result = mvc.perform(get("/my-tenants").with(john())).andReturn();
        model = result.getModelAndView().getModel();
        listOfTenants = (List<Tenant>)model.get("tenants");
        Assert.assertTrue(listOfTenants.size() == 1);
	}

    // test inviting user
    @Test
	public void testInviteUser() throws Exception {
        // create tenant
        mvc.perform(post("/tenants").param("name", "HomeSweetHome").with(john()).with(csrf()));
        
        // get the ID of the tenant
        MvcResult result = mvc.perform(get("/my-tenants").with(john())).andReturn();
        Map<String, Object>  model = result.getModelAndView().getModel();
        List<Tenant> listOfTenants = (List<Tenant>)model.get("tenants");
        Tenant createdTenant = listOfTenants.get(0);
        String tenantId = createdTenant.getId();

        // invite bob
        mvc.perform(post(String.format("/tenants/%s/invitations", tenantId)).param("email", "bob.ali@tmail.com")
            .with(john()).with(csrf()))
            .andExpect(status().is3xxRedirection());

        // let's see if mohammad has an invitation
        result = mvc.perform(get("/my-tenants").with(bob()))
            .andReturn();
        
        model = result.getModelAndView().getModel();
        List<Invitation> invitations = (List<Invitation>)model.get("invitations");
        Assert.assertTrue(invitations.size() == 1);
        String invitationId = invitations.get(0).getId();

        // accept the invitation
        result = mvc.perform(get(String.format("/invitations/%s/accept", invitationId)).with(bob()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // verify that bob is in tenant
        result = mvc.perform(get("/my-tenants").with(bob())).andReturn();
        model = result.getModelAndView().getModel();
        listOfTenants = (List<Tenant>)model.get("tenants");
        Tenant theTenantThatbobJoined = listOfTenants.get(0);
        Assert.assertEquals(theTenantThatbobJoined.getId(), tenantId);

        // remove bob from tenant
        mvc.perform(get(String.format("/tenants/%s/members/%s/remove", tenantId, "bob")).with(john())).andExpect(status().is3xxRedirection());
        
        // verify that bob not in tenant
        result = mvc.perform(get("/my-tenants").with(bob())).andReturn();
        model = result.getModelAndView().getModel();
        listOfTenants = (List<Tenant>)model.get("tenants");
        Assert.assertTrue(listOfTenants.isEmpty());

	}


	
}