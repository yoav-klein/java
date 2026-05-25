package com.example.business;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mysql.cj.jdbc.MysqlDataSource;


@Configuration
@ComponentScan
@EnableTransactionManagement
public class SpringBusinessConfig implements WebMvcConfigurer {
    public SpringBusinessConfig() {
        super();
    }

    
    @Bean
    public PlatformTransactionManager txManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
    
    @Bean
    @Profile("test")
    public DataSource testDataSource() {
        String dbUser = "yoav";
        String dbPassword = "yoav";
        String dbHost = "localhost";
        int dbPort = 3306;
        String dbName = "test";

        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setServerName(dbHost);
        mysqlDS.setPort(dbPort);
        mysqlDS.setUser(dbUser);
        mysqlDS.setPassword(dbPassword);
        mysqlDS.setDatabaseName(dbName);

        return mysqlDS;
        
    }

    @Bean
    @Profile("default")
    public DataSource dataSource() {
        String dbUser = "yoav";
        String dbPassword = "yoav";
        String dbHost = "localhost";
        int dbPort = 3306;
        String dbName = "test";

        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setServerName(dbHost);
        mysqlDS.setPort(dbPort);
        mysqlDS.setUser(dbUser);
        mysqlDS.setPassword(dbPassword);
        mysqlDS.setDatabaseName(dbName);

        return mysqlDS;
        
    }
}