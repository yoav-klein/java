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

@Repository
public class TenantRepository {
    private static final String GET_ALL_TENANTS = "SELECT * FROM tenant_system.tenant";
    private static final String CREATE_TENANT_IN_TENANT_TABLE = "INSERT INTO tenant_system.tenant VALUES(?, ?, ?)";
    private static final String JOIN_USER_TO_TENANT_BY_ID = "INSERT INTO tenant_system.tenant_user VALUES(?, ?)";
    private static final String GET_ALL_TENANTS_FOR_USER = "SELECT * FROM tenant_system.tenant WHERE id IN " + 
        "(SELECT tenant_id FROM tenant_system.tenant_user WHERE user_id = (SELECT id FROM tenant_system.user WHERE name = ?))";
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
        tenant.setOwnerId(resultSet.getString("owner_id"));

        return tenant;
    };


    // not used
    public List<Tenant> getAllTenants() {
        return this.jdbcTemplate.query(GET_ALL_TENANTS, tenantRowMapper);
    }

    public List<Tenant> getAllTenantsForUser(String userName) {
        return this.jdbcTemplate.query(GET_ALL_TENANTS_FOR_USER, tenantRowMapper, userName);
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
                pstmt1.setString(3, userId);
                
                int rows = pstmt1.executeUpdate();
                System.out.println("Rows affected: " + rows);
                
                pstmt2.setString(1, tenantId);
                pstmt2.setString(2, userId);
    
                rows = pstmt2.executeUpdate();
                System.out.println("Rows affected: " + rows);

                // CREATE DATABASE causes an implicit commit, so we don't need to call commit
                stmt.execute("CREATE DATABASE " + tenantId);

                return stmt.execute(String.format(INIT_TENANT_TABLES, tenantId));
            }
        });
        
    }

    public void joinToTenant(String tenantId, String userId) {
        this.jdbcTemplate.update(JOIN_USER_TO_TENANT_BY_ID, tenantId, userId);
    }

    public Tenant getTenantById(String id) {
        return new Tenant();
    }

    public boolean isUserPartOfTenant(String userId, String tenantId) {
        return this.jdbcTemplate.queryForObject(IS_USER_PART_OF_TENANT, Integer.class, tenantId, userId) > 0;
    }
}
