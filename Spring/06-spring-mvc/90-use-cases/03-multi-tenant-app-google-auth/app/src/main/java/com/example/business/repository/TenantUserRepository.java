package com.example.business.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.business.model.Tenant;
import com.example.business.model.User;

@Repository
public class TenantUserRepository {
    private static final String ADD_USER_TO_TENANT = "INSERT INTO tenant_system.tenant_user(tenant_id, user_id, role) VALUES(?, ?, ?)";
    // private static final String REMOVE_ALL_TENANT_MEMBERS = "DELETE FROM tenant_system.tenant_user WHERE tenant_id = ?";
    private static final String REMOVE_USER_FROM_TENANT = "DELETE FROM tenant_system.tenant_user WHERE tenant_id = ? AND user_id = ?";
    private static final String GET_ALL_TENANTS_FOR_USER = "SELECT tenant_id FROM tenant_system.tenant_user WHERE user_id = ?";
    private static final String GET_ALL_USERS_FOR_TENANT = "SELECT user_id FROM tenant_system.tenant_user WHERE tenant_id = ?";
    private static final String IS_USER_PART_OF_TENANT = "SELECT COUNT(*) AS COUNT FROM tenant_system.tenant_user WHERE tenant_id = ? AND user_id = ?";
    private static final String GET_ROLE_OF_USER = "SELECT role FROM tenant_system.tenant_user WHERE tenant_id = ? AND user_id = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    UserRepository userRepository;
    
    
    public TenantUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addUserToTenant(String tenantId, String userId, String role) {
        this.jdbcTemplate.update(ADD_USER_TO_TENANT, tenantId, userId, role);
    }

    public void removeUserFromTenant(String tenantId, String userId) {
        this.jdbcTemplate.update(REMOVE_USER_FROM_TENANT, tenantId, userId);
    }

    public List<Tenant> getAllTenantsForUser(String userId) {
        List<String> tenantIdList = this.jdbcTemplate.queryForList(GET_ALL_TENANTS_FOR_USER, String.class, userId);

        List<Tenant> tenantList = new ArrayList<>();
        for(String id : tenantIdList) {
            tenantList.add(tenantRepository.getTenantById(id));
        }

        return tenantList;
    }

    public List<User> getAllUsersForTenant(String tenantId) {
        List<String> userIdList = this.jdbcTemplate.queryForList(GET_ALL_USERS_FOR_TENANT, String.class, tenantId);
        System.out.println("getAllUsersForTenant, tenant: " + tenantId);
        
        List<User> userList = new ArrayList<>();
        for(String id : userIdList) {
            System.out.println("user: " + id);
            userList.add(userRepository.getUserById(id).get());
        }
        
        return userList;
    }
    /* public void removeAllTenantMembers(String tenantId) {
        this.jdbcTemplate.update(REMOVE_ALL_TENANT_MEMBERS, tenantId);
    } */
    public boolean isUserPartOfTenant(String userId, String tenantId) {
        return this.jdbcTemplate.queryForObject(IS_USER_PART_OF_TENANT, Integer.class, tenantId, userId) > 0;
    }

    public String getUserRole(String userId, String tenantId) {
        return this.jdbcTemplate.queryForObject(GET_ROLE_OF_USER, String.class, tenantId, userId);
    }
}
