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

public class PromotionTests extends TenantBase {
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
    public void testPromoteToAdmin() throws Exception {
        acceptedInvitation();

        // promote john to admin
        MvcResult result = mvc.perform(post(String.format("/tenants/%s/members/john/promote", this.tenantId)).with(yoav()).with(csrf()))
            .andExpect(status().is3xxRedirection()).andReturn();
        
        // check if john is admin
        result = mvc.perform(get("/my-tenants").with(john())).andReturn();
        Map<String, Object> model = result.getModelAndView().getModel();
        
        List<TenantMembership> tenantMemberships = (List<TenantMembership>)model.get("tenantMemberships");
        Assert.assertTrue(tenantMemberships.get(0).getRole().equals("admin"));

    }

    @Test
    public void testAdminKicksAdmin() throws Exception {
        acceptedInvitation();
        
        // wait a second so that john is less senior that yoav
        Thread.sleep(1500);

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

    
}
