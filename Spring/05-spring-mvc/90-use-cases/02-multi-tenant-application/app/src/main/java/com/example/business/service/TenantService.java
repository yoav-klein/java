package com.example.business.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.business.model.Tenant;
import com.example.business.repository.TenantRepository;

@Service
public class TenantService {
    private static int uid = 0;

    @Autowired
    TenantRepository tenantRepository;    

    public List<Tenant> getAllTenants() {
        return tenantRepository.getAllTenants();
    }

    public void createTenant(String tenantName, String owner) {
        tenantRepository.createTenant(String.valueOf(++uid), tenantName, owner);
    }

    public Tenant getTenantById(String id) {
        return tenantRepository.getTenantById(id);
    }

    public void joinToTenant(String tenantId, String userName) {
        tenantRepository.joinToTenant(tenantId, userName);
    }
}
