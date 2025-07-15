package com.example.business.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.example.business.model.TenantMembership;
import com.example.business.repository.TenantUserRepository;

@Service("tenantUserService")
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

    @PreAuthorize("@authz.isAdmin(authentication, #tenantId)")
    public void promoteToAdmin(@P("tenantId") String tenantId, @P("userId") String userId) {
        tenantUserRepository.promoteToAdmin(tenantId, userId);
    }

    @PreAuthorize("@authz.isAdmin(authentication, #tenantId) or @authz.isUser(authentication, #userId)")
    public void removeUserFromTenant(@P("tenantId") String tenantId, @P("userId") String userId) {
        List<TenantMembership> memberships = tenantUserRepository.getAllUsersForTenant(tenantId);

        // if last user in tenant, delete tenant and return
        if(memberships.size() == 1) {
            tenantService.deleteTenant(tenantId);
            return;
        }
        tenantUserRepository.removeUserFromTenant(tenantId, userId);

        // if no admin left, assign a new admin based on seniority
        memberships = tenantUserRepository.getAllUsersForTenant(tenantId);
        if(!memberships.stream().anyMatch(membership -> membership.getRole().equals("admin"))) {
            // sort the list by seniority
            Collections.sort(memberships, new Comparator<TenantMembership>() {
                @Override
                public int compare(TenantMembership a, TenantMembership b) {
                    return a.getSince().compareTo(b.getSince());
                }
            });
            // promote the most senior member in the tenant to admin
            this.promoteToAdmin(tenantId, memberships.get(0).getUser().getId());
        }
    }

    public void addUserToTenant(String tenantId, String userId, String role) {
        tenantUserRepository.addUserToTenant(tenantId, userId, role);
    }

    public boolean isStrongerMember(String tenantId, String firstMemberId, String secondMemberId) throws Exception {
        // if first member is not admin, false
        if(!this.isAdmin(firstMemberId, tenantId)) return false;

        List<TenantMembership> allMembers = this.getAllUsersForTenant(tenantId);

        TenantMembership firstMember = allMembers.stream()
            .filter(member -> member.getUser().getId().equals(firstMemberId))
            .findFirst()
            .orElseThrow(() -> new Exception("Not found"));
        
        TenantMembership secondMember = allMembers.stream()
            .filter(member -> member.getUser().getId().equals(secondMemberId))
            .findFirst()
            .orElseThrow(() -> new Exception("Not found"));
        
        
        if(!secondMember.getRole().equals("admin")) return true;
        if(firstMember.getAdminSince().isBefore(secondMember.getAdminSince())) return true;

        return false;
        
    }



}
