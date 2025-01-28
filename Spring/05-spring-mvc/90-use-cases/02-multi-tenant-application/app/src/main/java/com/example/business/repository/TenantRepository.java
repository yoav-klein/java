package com.example.business.repository;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.business.model.Tenant;

@Repository
public class TenantRepository {
    private static final String GET_ALL_TENANTS = "SELECT * FROM tenant_system.tenant";
    private static final String CREATE_TENANT_IN_TENANT_TABLE = "INSERT INTO tenant_system.tenant VALUES(?, ?, (SELECT id FROM tenant_system.user WHERE name = ?))";
    private static final String JOIN_USER_TO_TENANT = "INSERT INTO tenant_system.tenant_user VALUES(?, (SELECT id FROM tenant_system.user WHERE name = ?))";
    
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


    public List<Tenant> getAllTenants() {
        return this.jdbcTemplate.query(GET_ALL_TENANTS, tenantRowMapper);
    }

    public void createTenant(String tenantId, String tenantName, String userName) {
        try(Connection conn = this.dataSource.getConnection()) {
            conn.setAutoCommit(false);

            try(PreparedStatement pstmt1 = conn.prepareStatement(CREATE_TENANT_IN_TENANT_TABLE);
                PreparedStatement pstmt2 = conn.prepareStatement(JOIN_USER_TO_TENANT);
                Statement stmt = conn.createStatement()) {

                pstmt1.setString(1, tenantId);
                pstmt1.setString(2, tenantName);
                pstmt1.setString(3, userName);
                
                int rows = pstmt1.executeUpdate();
                System.out.println("Rows affected: " + rows);            
                
                pstmt2.setString(1, tenantId);
                pstmt2.setString(2, userName);
    
                rows = pstmt2.executeUpdate();
                System.out.println("Rows affected: " + rows);
                
                
                // CREATE DATABASE causes an implicit commit, so we don't need to call commit
                stmt.execute("CREATE DATABASE " + tenantId);

            } catch(Exception e) {
                System.out.println("Caught exception, rollback");
                conn.rollback();
                throw e;
            }
        
        } catch(SQLException e) {
            System.out.println("SQL exception" + e);
        }
    }

    public void joinToTenant(String tenantId, String userName) {
        this.jdbcTemplate.update(JOIN_USER_TO_TENANT, tenantId, userName);
    }

    public Tenant getTenantById(String id) {
        return new Tenant();
    }
}
