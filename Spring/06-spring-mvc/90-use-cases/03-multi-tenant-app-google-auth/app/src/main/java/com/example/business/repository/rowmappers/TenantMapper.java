package com.example.business.repository.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.business.model.Tenant;

@Component
public class TenantMapper implements RowMapper<Tenant> {

    
    @Override
    public Tenant mapRow(ResultSet rs, int rowNum) throws SQLException {
        String tenantId = rs.getString("id");
        String name = rs.getString("name");
        
        Tenant tenant = new Tenant();
        tenant.setId(tenantId);
        tenant.setName(name);
        
        return tenant;
    }
}

