package com.example.business;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;


@Configuration
@ComponentScan
public class SpringBusinessConfig implements WebMvcConfigurer {
    public SpringBusinessConfig() {
        super();
    }

    
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
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