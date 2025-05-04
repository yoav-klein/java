package com.example.business.repository.rowmappers;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.business.model.Invitation;
import com.example.business.model.User;
import com.example.business.model.Tenant;
import com.example.business.repository.TenantRepository;
import com.example.business.repository.UserRepository;

@Component
public class InvitationMapper implements RowMapper<Invitation> {

    @Autowired
    private final UserRepository userRepository;
    
    @Autowired
    private final TenantRepository tenantRepository;

    @Override
    public Invitation mapRow(ResultSet rs, int rowNum) throws SQLException {
        String invitationId = rs.getString("id");
        Long tenantId = rs.getLong("tenant_id");
        Long userId = rs.getLong("user_id");

        User user = userRepository.getUserById(userId);
        Tenant tenant = tenantRepository.geTenantById(tenantId);

        return new Invitation(invitationId, tenant, user);
    }
}

