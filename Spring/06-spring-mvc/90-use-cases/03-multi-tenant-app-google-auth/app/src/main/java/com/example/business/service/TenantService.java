package com.example.business.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.example.business.exception.UserAlreadyInTenantException;
import com.example.business.model.Tenant;
import com.example.business.model.User;
import com.example.business.model.Invitation;
import com.example.business.repository.TenantRepository;
import com.example.business.repository.TenantUserRepository;
import com.example.business.repository.InvitationRepository;

@Service
public class TenantService {

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    UserService userService;

    @Autowired
    TenantUserRepository tenantUserRepository;

    @Autowired
    InvitationRepository invitationRepository;

    public List<Tenant> getAllTenantsForUser(String userId) {
        return tenantUserRepository.getAllTenantsForUser(userId);
    }

    public List<User> getAllUsersForTenant(String tenantId) {
        return tenantUserRepository.getAllUsersForTenant(tenantId);
    }

    // TRANSACTIONAL
    public void createTenant(String tenantName, String ownerId) {
        String tenantId = UUID.randomUUID().toString().replace("-", "");
        tenantRepository.createTenantSchema(tenantId);
        tenantRepository.createTenant(tenantId, tenantName);
        tenantUserRepository.addUserToTenant(tenantId, ownerId, "admin");
    }

    public Tenant getTenantById(String id) {
        return tenantRepository.getTenantById(id);
    }

    public void deleteTenant(String id) {
        tenantRepository.deleteTenant(id);
    }

    // TRANSACTIONAL
    public void inviteUser(String tenantId, String email) {
        User user = userService.getUserByEmail(email).get();
        // ERROR HANDLING - IF USER DOESN'T EXIST
        invitationRepository.addInvitation(tenantId, user.getId());

    }

    public void removeUserFromTenant(String tenantId, String userId) {
        tenantUserRepository.removeUserFromTenant(tenantId, userId);
    }

    // TRANSACTIONAL
    public void acceptInvitation(String invitationId) {
        Invitation invitation = invitationRepository.getInvitationById(invitationId);
        invitationRepository.removeInvitation(invitationId);
        tenantUserRepository.addUserToTenant(invitation.getTenant().getId(), invitation.getUser().getId(), "regular");
    }

    public void declineInvitation(String invitationId) {
        invitationRepository.removeInvitation(invitationId);
    }

    public void joinToTenant(String tenantId, String userId) {
        try {
            tenantUserRepository.addUserToTenant(tenantId, userId, "regular");
        } catch(DuplicateKeyException e) {
            throw new UserAlreadyInTenantException();
        }
    }

    public boolean isUserPartOfTenant(String userId, String tenantId) {
        return tenantUserRepository.isUserPartOfTenant(userId, tenantId);
    }

    public boolean isAdmin(String userId, String tenantId) {
        return tenantUserRepository.getUserRole(userId, tenantId).equals("admin");
    }
}
