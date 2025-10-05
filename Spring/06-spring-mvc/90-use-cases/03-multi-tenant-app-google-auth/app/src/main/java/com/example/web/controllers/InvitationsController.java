package com.example.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.business.service.InvitationService;
import com.example.business.exception.InvitationNotFoundException;


@Controller
@RequestMapping("/invitations")
public class InvitationsController {
    @Autowired
    private InvitationService invitationService;

    @PostMapping("/{id}/accept")
    public String acceptInvitation(@PathVariable("id") String invitationId) throws InvitationNotFoundException {
        invitationService.acceptInvitation(invitationId);
        return "redirect:/my-tenants";
    }

    @PostMapping("/{id}/decline")
    public String declineInvitation(@PathVariable("id") String invitationId) throws InvitationNotFoundException  {
        invitationService.declineInvitation(invitationId);
        return "redirect:/my-tenants";
    }

    @DeleteMapping("/{id}")
    public String cancelInvitation(@PathVariable("id") String invitationId) throws InvitationNotFoundException {
        String tenantId = invitationService.getInvitationById(invitationId).getTenant().getId();
        invitationService.declineInvitation(invitationId);
        return "redirect:/tenants/" + tenantId;
    }

}
