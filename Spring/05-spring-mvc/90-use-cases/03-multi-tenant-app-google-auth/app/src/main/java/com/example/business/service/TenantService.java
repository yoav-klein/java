package com.example.business.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.example.business.exception.UserAlreadyInTenantException;
import com.example.business.model.Tenant;
import com.example.business.repository.TenantRepository;

@Service
public class TenantService {

    @Autowired
    TenantRepository tenantRepository;    

    public List<Tenant> getAllTenantsForUser(String userName) {
        return tenantRepository.getAllTenantsForUser(userName);
    }

    public void createTenant(String tenantName, String ownerId) {
        System.out.println("==== tenant service:: creating tenant: " + tenantName + " user: " + ownerId);
        tenantRepository.createTenant(UUID.randomUUID().toString().replace("-", ""), tenantName, ownerId);
    }

    public Tenant getTenantById(String id) {
        return tenantRepository.getTenantById(id);
    }

    public void joinToTenant(String tenantId, String userName) {
        try {
            tenantRepository.joinToTenant(tenantId, userName);
        } catch(DuplicateKeyException e) {
            throw new UserAlreadyInTenantException();
        }
    }

    public boolean isUserPartOfTenant(String userName, String tenantId) {
        return tenantRepository.isUserPartOfTenant(userName, tenantId);
    }
}
