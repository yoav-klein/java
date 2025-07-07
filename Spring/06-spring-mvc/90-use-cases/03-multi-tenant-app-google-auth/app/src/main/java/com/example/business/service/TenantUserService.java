package com.example.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.example.business.model.TenantMembership;
import com.example.business.repository.TenantUserRepository;

@Service
public class TenantUserService {
    @Autowired
    TenantService tenantService;

    @Autowired
    TenantUserRepository tenantUserRepository;
    
    public List<TenantMembership> getAllTenantsForUser(String userId) {
        return tenantUserRepository.getAllTenantsForUser(userId);
    }

    public List<TenantMembership> getAllUsersForTenant(String tenantId) {
        return tenantUserRepository.getAllUsersForTenant(tenantId);
    }

    public boolean isUserPartOfTenant(String userId, String tenantId) {
        return tenantUserRepository.isUserPartOfTenant(userId, tenantId);
    }

    public boolean isAdmin(String userId, String tenantId) {
        return tenantUserRepository.isUserPartOfTenant(userId, tenantId) && tenantUserRepository.getUserRole(userId, tenantId).equals("admin");
    }

    @PreAuthorize("@authz.isAdmin(authentication, #tenantId) or @authz.isUser(authentication, #userId)")
    public void removeUserFromTenant(@P("tenantId") String tenantId, @P("userId") String userId) {
        System.out.println("TenantUserService:: RemoveUserFromTenant");
        tenantUserRepository.removeUserFromTenant(tenantId, userId);
        // if last user in tenant, delete tenant
        if(tenantUserRepository.getAllUsersForTenant(tenantId).isEmpty()) {
            tenantService.deleteTenant(tenantId);
        }
        System.out.println("TenantUserService:: RemoveUserFromTenant END");
    }



}
