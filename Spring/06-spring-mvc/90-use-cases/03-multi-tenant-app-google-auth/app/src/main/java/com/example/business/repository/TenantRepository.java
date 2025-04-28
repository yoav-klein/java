package com.example.business.repository;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.business.model.Tenant;
import com.example.business.model.Invitation;

@Repository
public class TenantRepository {
    private static final String GET_ALL_TENANTS = "SELECT * FROM tenant_system.tenants";
    private static final String GET_TENANT_BY_ID = "SELECT * FROM tenant_system.tenants WHERE id = ?";
    private static final String CREATE_TENANT_IN_TENANT_TABLE = "INSERT INTO tenant_system.tenants VALUES(?, ?)";
    private static final String JOIN_USER_TO_TENANT = "INSERT INTO tenant_system.tenant_user(tenant_id, user_id, role) VALUES(?, ?, ?)";
    private static final String DELETE_TENANT_IN_TENANT_TABLE = "DELETE FROM tenant_system.tenants WHERE id = ?";
    private static final String REMOVE_ALL_TENANT_MEMBERS = "DELETE FROM tenant_system.tenant_user WHERE tenant_id = ?";
    private static final String REMOVE_ALL_TENANT_INVITATIONS = "DELETE FROM tenant_system.invitations WHERE tenant_id = ?";
    private static final String ADD_INVITATION = "INSERT INTO tenant_system.invitations VALUES(?, ?)";
    private static final String REMOVE_USER_FROM_TENANT = "DELETE FROM tenant_system.tenant_user WHERE tenant_id = ? AND user_id = ?";
    private static final String GET_INVITATION_BY_ID = "SELECT * FROM tenant_system.invitations WHERE id = ?"; 
    private static final String GET_ALL_TENANTS_FOR_USER = "SELECT * FROM tenant_system.tenants WHERE id IN " + 
        "(SELECT tenant_id FROM tenant_system.tenant_user WHERE user_id = ?)";
    private static final String IS_USER_PART_OF_TENANT = "SELECT COUNT(*) AS COUNT FROM tenant_system.tenant_user WHERE tenant_id = ? AND user_id = ?";
    private static final String INIT_TENANT_TABLES = "CREATE TABLE %s.product(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50))";


    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    
    public TenantRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    

    private final RowMapper<Tenant> tenantRowMapper = (resultSet, rowNum) -> {
        Tenant tenant = new Tenant();
        tenant.setId(resultSet.getString("id"));
        tenant.setName(resultSet.getString("name"));
        
        return tenant;
    };
    
    private final RowMapper<Tenant> invitationRowMapper = (resultSet, rowNum) -> {
        Invitation invitation = new Invitation();
        invitation.setId(resultSet.getString("id"));
        invitation.setTenantId(resultSet.getString("tenant_id"));
        invitation.setUserId(resultSet.getString("user_id"));
        
        return invitation;
    };


    // not used
    public List<Tenant> getAllTenants() {
        return this.jdbcTemplate.query(GET_ALL_TENANTS, tenantRowMapper);
    }

    public List<Tenant> getAllTenantsForUser(String userId) {
        return this.jdbcTemplate.query(GET_ALL_TENANTS_FOR_USER, tenantRowMapper, userId);
    }

    public void createTenant(String tenantId, String tenantName, String userId) {
        System.out.println("==== tenant repository:: creating tenant: " + tenantName + " user: " + userId);
        this.jdbcTemplate.execute((Connection conn) -> {
            
            conn.setAutoCommit(false);

            try(PreparedStatement pstmt1 = conn.prepareStatement(CREATE_TENANT_IN_TENANT_TABLE);
                PreparedStatement pstmt2 = conn.prepareStatement(JOIN_USER_TO_TENANT_BY_ID);
                Statement stmt = conn.createStatement()) {

                pstmt1.setString(1, tenantId);
                pstmt1.setString(2, tenantName);
                
                int rows = pstmt1.executeUpdate();
                 
                pstmt2.setString(1, tenantId);
                pstmt2.setString(2, userId);
                pstmt2.setString(3, "admin");
    
                rows = pstmt2.executeUpdate();
                System.out.println("Rows affected: " + rows);

                // CREATE DATABASE causes an implicit commit, so we don't need to call commit
                
                stmt.execute("CREATE DATABASE " + "tenant_" + tenantId);

                return stmt.execute(String.format(INIT_TENANT_TABLES, tenantId));
            }
        });
        
    }

    public void deleteTenant(String tenantId) {
        this.jdbcTemplate.execute((Connection conn) -> {
            conn.setAutoCommit(false);

            try(PreparedStatement pstmt1 = conn.preparetStatement(DELETE_TENANT_IN_TENANT_TABLE);
                PreparedStatement pstmt2 = conn.prepareStatement(REMOVE_ALL_TENANT_MEMBERS);
                PreparedStatement pstmt3 = conn.prepareStatement(REMOVE_ALL_TENANT_INVITATIONS);
                Statement stmt = conn.createStatement()) {
                
                pstmt1.setString(1, tenantId);
                int rows = pstmt1.executeUpdate();

                pstmt2.setString(1, tenantId);
                rows = pstmt2.executeUpdate();

                pstmt3.setString(1, tenantId);
                rows = pstmt3.executeUpdate();

                stmt.execute("DROP DATABASE " + "tenant_" + tenantId);
            }

        })
    }

    public void inviteUserToTenant(String tenantId, String userId) {
        this.jdbcTemplate.update(ADD_INVITATION, tenantId, userId);
    }

    public void removeUserFromTenant(String tenantId, String userId) {
        this.jdbcTemplate.update(REMOVE_USER_FROM_TENANT, tenantId, userId);
    }

    public Invitation getInvitationById(String invitationId) {
        return this.jdbcTemplate.queryForObject(GET_INVITATION_BY_ID, invitationRowMapper, invitationId);
    }

    private void joinUserToTenant(String tenantId, String userId) {
        this.jdbcTemplate.update(JOIN_USER_TO_TENANT, tenantId, userId, "regular");
    }

    public Tenant getTenantById(String tenantId) {
        return this.jdbcTemplate.queryForObject(GET_TENANT_BY_ID, tenantRowMapper, tenantId);
    }

    public boolean isUserPartOfTenant(String userId, String tenantId) {
        return this.jdbcTemplate.queryForObject(IS_USER_PART_OF_TENANT, Integer.class, tenantId, userId) > 0;
    }
}
