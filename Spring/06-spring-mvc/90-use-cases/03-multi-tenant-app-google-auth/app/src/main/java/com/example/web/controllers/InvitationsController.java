package com.example.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.business.service.TenantService;


@Controller
@RequestMapping("/invitations")
public class InvitationsController {
    @Autowired
    private TenantService tenantService;

    @GetMapping("/{id}/accept")
    public String acceptInvitation(@PathVariable("id") String invitationId) {
        tenantService.acceptInvitation(invitationId);
        return "redirect:/my-tenants";
    }

    @GetMapping("/{id}/decline")
    public String declineInvitation(@PathVariable("id") String invitationId) {
        tenantService.declineInvitation(invitationId);
        return "redirect:/my-tenants";
    }

    @GetMapping("/{id}/cancel")
    public String cancelInvitation(@PathVariable("id") String invitationId) {
        String tenantId = tenantService.getInvitationById(invitationId).getTenant().getId();
        tenantService.declineInvitation(invitationId);
        return "redirect:/tenants/" + tenantId;
    }

}
