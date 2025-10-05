package com.example.business.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.example.business.exception.UserAlreadyInTenantException;
import com.example.business.exception.UserAlreadyInvitedException;
import com.example.business.exception.UserNotFoundException;
import com.example.business.model.Invitation;
import com.example.business.model.Tenant;
import com.example.business.model.User;
import com.example.business.repository.InvitationRepository;
import com.example.business.repository.TenantRepository;
import com.example.business.repository.TenantUserRepository;

@Service("tenantService")
public class TenantService {

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    UserService userService;

    @Autowired
    TenantUserRepository tenantUserRepository;

    @Autowired
    TenantUserService tenantUserService;

    @Autowired
    InvitationRepository invitationRepository;

    // TRANSACTIONAL
    public Tenant createTenant(String tenantName, String ownerId) {
        String tenantId = UUID.randomUUID().toString().replace("-", "");
        tenantRepository.createTenantSchema(tenantId);
        tenantRepository.createTenant(tenantId, tenantName);
        tenantUserService.addUserToTenant(tenantId, ownerId, "admin");

        Tenant tenant = new Tenant();
        tenant.setId(tenantId);
        tenant.setName(tenantName);

        return tenant;
    }

    public Tenant getTenantById(String id) {
        return tenantRepository.findTenantById(id).get();
    }

    @PreAuthorize("@tenantUserService.isMostSenior(@authz.getUserIdFromAuthentication(authentication), #tenantId)")
    public void deleteTenant(@P("tenantId") String tenantId) {
        tenantRepository.deleteTenant(tenantId);
        tenantRepository.deleteTenantSchema(tenantId);
    }

    // TRANSACTIONAL
    @PreAuthorize("@authz.isAdmin(authentication, #tenantId)")
    public Invitation inviteUser(@P("tenantId") String tenantId, String email) throws UserNotFoundException, UserAlreadyInTenantException, UserAlreadyInvitedException {
        String invitationId = UUID.randomUUID().toString().replace("-", "");

        User user = userService.getUserByEmail(email);
        if(tenantUserRepository.isUserPartOfTenant(user.getId(), tenantId)) {
            throw new UserAlreadyInTenantException();
        }
        
        try {
            invitationRepository.addInvitation(invitationId, tenantId, user.getId());
        } catch(DuplicateKeyException e) {
            throw new UserAlreadyInvitedException();
        }
        
        Tenant tenant = getTenantById(tenantId);
        return new Invitation(invitationId, tenant, user);
    }

    public List<Invitation> getAllInvitationsForTenant(String tenantId) {
        return invitationRepository.getAllInvitationsForTenant(tenantId);
    }

}
