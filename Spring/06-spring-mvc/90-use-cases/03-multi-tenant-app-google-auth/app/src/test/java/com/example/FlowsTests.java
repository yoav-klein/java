package com.example;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import  org.springframework.test.web.servlet.request.RequestPostProcessor;
import  static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import  static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.business.model.Invitation;
import com.example.business.model.Tenant;
import com.example.business.model.TenantMembership;
import com.example.business.model.User;
import com.example.business.service.UserService;

public class FlowsTests extends TenantBase {
    
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

    public RequestPostProcessor bob() {
        User user = new User("bob", "Bob Ali", "bob.ali@tmail.com", "https://bob.picture.com");
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

    // invite john by yoav
    private String inviteUser() throws Exception {
        // invite john
        MvcResult result = mvc.perform(post(String.format("/tenants/%s/invitations", this.tenantId)).param("email", "john.adams@tmail.com")
            .with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andReturn();
        
        String invitationId = (String)result.getFlashMap().get("invitationId");

        return invitationId;
	}

    // john accepts invitation
    private void acceptedInvitation() throws Exception {
        String invitationId = inviteUser();
        // accept the invitation
        MvcResult result = mvc.perform(post(String.format("/invitations/%s/accept", invitationId)).with(john()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
	}

    private List<Tenant> getTenantsForJohn() throws Exception {
        MvcResult result = mvc.perform(get("/my-tenants").with(john())).andReturn();
        Map<String, Object> model = result.getModelAndView().getModel();
        
        List<TenantMembership> tenantMemberships = (List<TenantMembership>)model.get("tenantMemberships");
        return tenantMemberships.stream().map(membership -> membership.getTenant()).toList();
    }

    @Test
    public void testInviteUser() throws Exception {
        String invitationId = inviteUser();
        
        // let's see if john has an invitation
        MvcResult result = mvc.perform(get("/my-tenants").with(john()))
            .andReturn();
        
        Map<String, Object> model = result.getModelAndView().getModel();
        List<Invitation> invitations = (List<Invitation>)model.get("invitations");

        Assert.assertTrue(invitations.stream().anyMatch(invitation -> invitation.getId().equals(invitationId)));
    }
    
    @Test
    public void testAcceptInvitation() throws Exception {
        String invitationId = inviteUser();

        // accept the invitation
        MvcResult result = mvc.perform(post(String.format("/invitations/%s/accept", invitationId)).with(john()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // verify that john is in tenant
        List<Tenant> johnTenants = getTenantsForJohn();
        Tenant theTenantThatJohnJoined = johnTenants.get(0);
        Assert.assertEquals(theTenantThatJohnJoined.getId(), this.tenantId);
    }
    @Test
    public void testAcceptInvitationByUnauthorizedUser() throws Exception {
        String invitationId = inviteUser();
        
        // accept the invitation
        mvc.perform(post(String.format("/invitations/%s/accept", invitationId)).with(bob()).with(csrf()))
        .andExpect(status().is4xxClientError());
        
    }
        
    @Test
    public void testDeclineInvitation() throws Exception {
        String invitationId = inviteUser();

        // decline the invitation
        MvcResult result = mvc.perform(post(String.format("/invitations/%s/decline", invitationId)).with(john()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
            
        // verify that john is NOT in tenant
        List<Tenant> johnTenants = getTenantsForJohn();
        
        Assert.assertTrue(johnTenants.stream().allMatch(tenant -> !tenant.getId().equals(this.tenantId)));

        // verify that invitation doesn't exist
        result = mvc.perform(get("/my-tenants").with(john())).andReturn();
        Map<String, Object> model = result.getModelAndView().getModel();
        List<Invitation> invitations = (List<Invitation>)model.get("invitations");
        
        Assert.assertTrue(invitations.stream().allMatch(invitation -> !invitation.getId().equals(invitationId)));
    }
    
    
    @Test
    public void testCancelInvitation() throws Exception {
        String invitationId = inviteUser();

        // accept the invitation
        MvcResult result = mvc.perform(delete(String.format("/invitations/%s", invitationId)).with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // verify that invitation doesn't exist
        result = mvc.perform(get("/my-tenants").with(john())).andReturn();
        Map<String, Object> model = result.getModelAndView().getModel();
        List<Invitation> invitations = (List<Invitation>)model.get("invitations");
        
        Assert.assertTrue(invitations.stream().allMatch(invitation -> !invitation.getId().equals(invitationId)));
    }

    @Test
    public void testUserLeavesTenant() throws Exception {
        acceptedInvitation();

        // accept the invitation
        MvcResult result = mvc.perform(delete(String.format("/tenants/%s/members/john/leave", this.tenantId)).with(john()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        List<Tenant> johnTenants = getTenantsForJohn();
        Assert.assertTrue(johnTenants.stream().allMatch(tenant -> !tenant.getId().equals(this.tenantId)));

    }

    @Test
    public void testAdminKicksUser() throws Exception {
        acceptedInvitation();
    
        // remove john with yoav (admin)
        MvcResult result = mvc.perform(delete(String.format("/tenants/%s/members/john", this.tenantId)).with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // verify that john is NOT in tenant
        List<Tenant> johnTenants = getTenantsForJohn();
        Assert.assertTrue(johnTenants.stream().allMatch(tenant -> !tenant.getId().equals(this.tenantId)));
    }
    
    @Test
    public void testAdminKicksUserNotAuthorized() throws Exception {
        acceptedInvitation();

        // remove john with bob (not admin)
        MvcResult result = mvc.perform(delete(String.format("/tenants/%s/members/john", this.tenantId)).with(bob()).with(csrf()))
            .andExpect(status().is(403)).andReturn();
        
        // verify that john IS in tenant
        List<Tenant> johnTenants = getTenantsForJohn();
        Assert.assertTrue(johnTenants.stream().anyMatch(tenant -> tenant.getId().equals(this.tenantId)));
    }

    @Test
    public void testPromoteToAdmin() throws Exception {
        acceptedInvitation();

        // promote john to admin
        MvcResult result = mvc.perform(post(String.format("/tenants/%s/members/john/promote", this.tenantId)).with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
    }

    @Test
    public void testAdminKicksAdmin() throws Exception {
        acceptedInvitation();

        // promote john to admin
        MvcResult result = mvc.perform(post(String.format("/tenants/%s/members/john/promote", this.tenantId)).with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();

        // yoav kicks john
        result = mvc.perform(delete(String.format("/tenants/%s/members/john", this.tenantId)).with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // verify that john is NOT in tenant
        List<Tenant> johnTenants = getTenantsForJohn();
        Assert.assertTrue(johnTenants.stream().allMatch(tenant -> !tenant.getId().equals(this.tenantId)));

    }

    @Test
    public void testAdminKicksAdminNotAuthorized() throws Exception {
        acceptedInvitation();

        // promote john to admin
        MvcResult result = mvc.perform(post(String.format("/tenants/%s/members/john/promote", this.tenantId)).with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();

        // john kicks yoav
        result = mvc.perform(delete(String.format("/tenants/%s/members/yoav", this.tenantId)).with(john()).with(csrf()))
            .andExpect(status().is(403)).andReturn();
        
        // verify that yoav IS in tenant
        List<Tenant> johnTenants = getTenantsForJohn();
        Assert.assertTrue(johnTenants.stream().anyMatch(tenant -> tenant.getId().equals(this.tenantId)));

    }

    @Test
    public void testAutoPromotion() throws Exception {
        acceptedInvitation();

        // wait a second before accepting bob as a user
        Thread.sleep(1000);
        // invite bob to tenant
        MvcResult result = mvc.perform(post(String.format("/tenants/%s/invitations", this.tenantId)).param("email", "bob.ali@tmail.com")
            .with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andReturn();
        
        String invitationId = (String)result.getFlashMap().get("invitationId");
        // accept the invitation
        result = mvc.perform(post(String.format("/invitations/%s/accept", invitationId)).with(bob()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // now yoav (admin) leaves tenant
        result = mvc.perform(delete(String.format("/tenants/%s/members/yoav/leave", this.tenantId)).with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // now john should be the admin
        result = mvc.perform(get("/my-tenants").with(john())).andReturn();
        Map<String, Object> model = result.getModelAndView().getModel();
        List<TenantMembership> tenantMemberships = (List<TenantMembership>)model.get("tenantMemberships");
        Assert.assertTrue(tenantMemberships.get(0).getRole().equals("admin"));

        result = mvc.perform(get("/my-tenants").with(bob())).andReturn();
        model = result.getModelAndView().getModel();
        tenantMemberships = (List<TenantMembership>)model.get("tenantMemberships");
        Assert.assertTrue(tenantMemberships.get(0).getRole().equals("regular"));

    }

    @Test
    public void testConnectToTenant() throws Exception {
        acceptedInvitation();

        MvcResult result = mvc.perform(get(String.format("/tenants/%s", this.tenantId)).with(john()))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    public void testConnectToTenantNotAuthorized() throws Exception {
        acceptedInvitation();

        MvcResult result = mvc.perform(get(String.format("/tenants/%s", this.tenantId)).with(bob()))
            .andExpect(status().is(403))
            .andReturn();
    }
	
}