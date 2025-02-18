package org.example;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@ComponentScan
public class AppConfig  {
	@Bean
	DataSource getDataSource() {
		String dbUrl = "jdbc:mysql://localhost:3306/test";
        String dbUser = "yoav";
        String dbPassword = "yoav";

        MysqlDataSource mysqlDS = null;
        
        mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(dbUrl);
        mysqlDS.setUser(dbUser);
        mysqlDS.setPassword(dbPassword);

        return mysqlDS;
	}

    @Bean
	static public PersistenceExceptionTranslationPostProcessor translation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
