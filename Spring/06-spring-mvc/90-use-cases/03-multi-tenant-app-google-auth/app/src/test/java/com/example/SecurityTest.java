package com.example;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import  static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import  org.springframework.test.web.servlet.request.RequestPostProcessor;
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
public class SecurityTest extends TenantBase {
    
	@Autowired
	private WebApplicationContext context;

    @Autowired
    UserService userService;

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


    // test inviting user
    @Test
	public void testInviteUser() throws Exception {
        
        // invite john
        mvc.perform(post(String.format("/tenants/%s/invitations", this.tenant.getId())).param("email", "john.adams@tmail.com")
            .with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection());

        // let's see if john has an invitation
        MvcResult result = mvc.perform(get("/my-tenants").with(john()))
            .andReturn();
        
        Map<String, Object> model = result.getModelAndView().getModel();
        List<Invitation> invitations = (List<Invitation>)model.get("invitations");
        Assert.assertTrue(invitations.size() == 1);
	}
    
    @Test(dependsOnMethods = { "testInviteUser" })
    public void testAcceptInvitation() throws Exception {
        MvcResult result = mvc.perform(get("/my-tenants").with(john()))
            .andReturn();
            
        Map<String, Object> model = result.getModelAndView().getModel();
        List<Invitation> invitations = (List<Invitation>)model.get("invitations");
        Assert.assertTrue(invitations.size() == 1);
        String invitationId = invitations.get(0).getId();
        
        // accept the invitation
        result = mvc.perform(post(String.format("/invitations/%s/accept", invitationId)).with(john()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // verify that john is in tenant
        result = mvc.perform(get("/my-tenants").with(john())).andReturn();
        model = result.getModelAndView().getModel();
        List<Tenant> listOfTenants = (List<Tenant>)model.get("tenants");
        Tenant theTenantThatJohnJoined = listOfTenants.get(0);
        Assert.assertEquals(theTenantThatJohnJoined.getId(), this.tenant.getId());
    }


	
}