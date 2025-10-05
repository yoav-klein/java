package com.example;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import  static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.business.model.Invitation;
import com.example.business.model.Tenant;
import com.example.business.model.TenantMembership;
import com.example.business.service.UserService;

public class InvitationTests extends TenantBase {
    @Autowired
    UserService userService;

    
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
    public void testInviteExistingUser() throws Exception {
        acceptedInvitation();
        
        // invite john after he's already in tenant
        MvcResult result = mvc.perform(post(String.format("/tenants/%s/invitations", this.tenantId)).param("email", "john.adams@tmail.com")
            .with(yoav()).with(csrf()))
            .andExpect(status().is(400))
            .andReturn();
    }

    @Test
    public void testInviteInvitedUser() throws Exception {
        String invitationId = inviteUser();
        
        // invite john after he's already invited
        MvcResult result = mvc.perform(post(String.format("/tenants/%s/invitations", this.tenantId)).param("email", "john.adams@tmail.com")
            .with(yoav()).with(csrf()))
            .andExpect(status().is(409))
            .andReturn();
    }

    @Test
    public void testInviteUserNotAuthorized() throws Exception {
        acceptedInvitation();

        MvcResult result = mvc.perform(post(String.format("/tenants/%s/invitations", this.tenantId)).param("email", "bob.ali@tmail.com")
            .with(john()).with(csrf()))
            .andExpect(status().is(403))
            .andReturn();
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
    public void testAcceptInvitationUnauthorized() throws Exception {
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
    public void testDeclineInvitationUnauthorized() throws Exception {
        String invitationId = inviteUser();

        // decline the invitation
        MvcResult result = mvc.perform(post(String.format("/invitations/%s/decline", invitationId)).with(bob()).with(csrf()))
            .andExpect(status().is(403)).andReturn();

    }
    
    
    @Test
    public void testCancelInvitation() throws Exception {
        String invitationId = inviteUser();

        // cancel invitation
        MvcResult result = mvc.perform(delete(String.format("/invitations/%s", invitationId)).with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // verify that invitation doesn't exist
        result = mvc.perform(get("/my-tenants").with(john())).andReturn();
        Map<String, Object> model = result.getModelAndView().getModel();
        List<Invitation> invitations = (List<Invitation>)model.get("invitations");
        
        Assert.assertTrue(invitations.stream().allMatch(invitation -> !invitation.getId().equals(invitationId)));
    }

    @Test
    public void testCancelInvitationNonExisting() throws Exception {
        String invitationId = inviteUser();

        // accept the invitation
        MvcResult result = mvc.perform(post(String.format("/invitations/%s/accept", invitationId)).with(john()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // verify that john is in tenant
        List<Tenant> johnTenants = getTenantsForJohn();
        Tenant theTenantThatJohnJoined = johnTenants.get(0);
        Assert.assertEquals(theTenantThatJohnJoined.getId(), this.tenantId);

        // try cancel invitation
        result = mvc.perform(delete(String.format("/invitations/%s", invitationId)).with(yoav()).with(csrf()))
            .andExpect(status().is(404)).andReturn();
    }

    @Test
    public void testAcceptInvitationNonExisting() throws Exception {
        String invitationId = inviteUser();

        // accept the invitation
        MvcResult result = mvc.perform(post(String.format("/invitations/%s/accept", invitationId)).with(john()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // verify that john is in tenant
        List<Tenant> johnTenants = getTenantsForJohn();
        Tenant theTenantThatJohnJoined = johnTenants.get(0);
        Assert.assertEquals(theTenantThatJohnJoined.getId(), this.tenantId);

        // try cancel invitation
        // accept the invitation
        result = mvc.perform(post(String.format("/invitations/%s/accept", invitationId)).with(john()).with(csrf()))
            .andExpect(status().is(404)).andReturn();
    }
}
