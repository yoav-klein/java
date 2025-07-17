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

public class FlowsTests extends TenantBase {

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
    public void testUserLeavesTenant() throws Exception {
        acceptedInvitation();

        // leave tenant
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
    public void testAdminKicksUserAfterUserLeft() throws Exception {
        acceptedInvitation();
    
        // john leaves
        MvcResult result = mvc.perform(delete(String.format("/tenants/%s/members/john/leave", this.tenantId)).with(john()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
            // verify that john is NOT in tenant
        List<Tenant> johnTenants = getTenantsForJohn();
        Assert.assertTrue(johnTenants.stream().allMatch(tenant -> !tenant.getId().equals(this.tenantId)));

        // admin kicks john
        result = mvc.perform(delete(String.format("/tenants/%s/members/john", this.tenantId)).with(yoav()).with(csrf()))
            .andExpect(status().is(400)).andReturn();
    }
    
    @Test
    public void testUserLeavesAfterKickedByAdmin() throws Exception {
        acceptedInvitation();

        // remove john with yoav (admin)
        MvcResult result = mvc.perform(delete(String.format("/tenants/%s/members/john", this.tenantId)).with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
            
        // verify that john is NOT in tenant
        List<Tenant> johnTenants = getTenantsForJohn();
        Assert.assertTrue(johnTenants.stream().allMatch(tenant -> !tenant.getId().equals(this.tenantId)));
        
        // john leaves
        result = mvc.perform(delete(String.format("/tenants/%s/members/john/leave", this.tenantId)).with(john()).with(csrf()))
            .andExpect(status().is(400)).andReturn();
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