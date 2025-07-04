package com.example.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.example.business.model.Invitation;
import com.example.business.repository.InvitationRepository;
import com.example.business.repository.TenantUserRepository;

@Service("invitationService")
public class InvitationService {
    @Autowired
    InvitationRepository invitationRepository;

    @Autowired
    TenantUserRepository tenantUserRepository;

    public String getInvitedUserId(String invitationId) {
        Invitation invitation = invitationRepository.getInvitationById(invitationId);
        return invitation.getUser().getId();
    }

    public String getInvitationTenantId(String invitationId) {
        Invitation invitation = invitationRepository.getInvitationById(invitationId);
        return invitation.getTenant().getId();
    }
    
    // TRANSACTIONAL
    // security - allowed only to the invited user
    @PreAuthorize("@authz.isUser(authentication, @invitationService.getInvitedUserId(#invitationId))")
    public void acceptInvitation(@P("invitationId") String invitationId) {
        Invitation invitation = invitationRepository.getInvitationById(invitationId);
        invitationRepository.removeInvitation(invitationId);
        tenantUserRepository.addUserToTenant(invitation.getTenant().getId(), invitation.getUser().getId(), "regular");
    }
    // @authz.isAdmin(authentication, @invitationService.getInvitationTenantId(#invitationId)) or 
    // security - allowed only to the invited user, or tenant admin
    @PreAuthorize("@authz.isAdmin(authentication, @invitationService.getInvitationTenantId(#invitationId)) or @authz.isUser(authentication, @invitationService.getInvitedUserId(#invitationId))")
    public void declineInvitation(@P("invitationId") String invitationId) {
        invitationRepository.removeInvitation(invitationId);
    }

    
    public Invitation getInvitationById(String id) {
        return invitationRepository.getInvitationById(id);
    }
}
