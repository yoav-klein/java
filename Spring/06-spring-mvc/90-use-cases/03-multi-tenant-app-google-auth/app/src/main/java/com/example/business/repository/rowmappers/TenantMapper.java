package com.example.business.repository.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.business.model.User;
import com.example.business.model.Tenant;
import com.example.business.repository.TenantUserRepository;

@Component
public class TenantMapper implements RowMapper<Tenant> {

    @Autowired
    private final TenantUserRepository tenantUserRepository;
    
    @Override
    public Tenant mapRow(ResultSet rs, int rowNum) throws SQLException {
        String tenantId = rs.getString("id");
        String name = rs.getString("name");
        List<User> members = tenantUserRepository.getAllUsersForTenant(tenantId);
        
        Tenant tenant = new Tenant();
        tenant.setId(tenantId);
        tenant.setName(name);
        tenant.setMembers(members);
        
        return tenant;
    }
}

