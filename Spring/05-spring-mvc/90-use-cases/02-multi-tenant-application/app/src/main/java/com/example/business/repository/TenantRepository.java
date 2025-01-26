package com.example.business.repository;

import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.business.model.Tenant;

@Repository
public class TenantRepository {
    private static final String GET_ALL_TENANTS = "SELECT * FROM tenant";
    private static final String CREATE_TENANTS = "INSERT INTO tenant VALUES(?, ?, (SELECT id FROM user WHERE name = ?))";
    private static final String JOIN_TENANT = "INSERT INTO tenant_user VALUES(?, (SELECT id FROM user WHERE name = ?))";
    
    private JdbcTemplate systemJdbcTemplate;
    private JdbcTemplate generalJdbcTemplate;

    public TenantRepository(@Qualifier("systemDataSource") DataSource systemDataSource,
        @Qualifier("generalDataSource") DataSource generalDataSource) {
        this.systemJdbcTemplate = new JdbcTemplate(systemDataSource);
        this.generalJdbcTemplate = new JdbcTemplate(generalDataSource);
    }
    

    private final RowMapper<Tenant> tenantRowMapper = (resultSet, rowNum) -> {
        Tenant tenant = new Tenant();
        tenant.setId(resultSet.getString("id"));
        tenant.setName(resultSet.getString("name"));
        tenant.setOwnerId(resultSet.getString("owner_id"));

        return tenant;
    };


    public List<Tenant> getAllTenants() {
        return this.systemJdbcTemplate.query(GET_ALL_TENANTS, tenantRowMapper);
    }

    public void createTenant(String tenantId, String tenantName, String userName) {
        this.systemJdbcTemplate.update(CREATE_TENANTS, tenantId, tenantName, userName);
        joinToTenant(tenantId, userName);

        generalJdbcTemplate.execute("CREATE DATABASE abc");
        // create tables in database
    }

    public void joinToTenant(String tenantId, String userName) {
        this.systemJdbcTemplate.update(JOIN_TENANT, tenantId, userName);
    }

    public Tenant getTenantById(String id) {
        return new Tenant();
    }
}
