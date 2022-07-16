package com.epam.esm.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private final Environment environment;

    @Autowired
    public DataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(environment.getProperty("driver"));
        bds.setUrl(environment.getProperty("url"));
        bds.setUsername(environment.getProperty("user"));
        bds.setPassword(environment.getProperty("password"));
        bds.setInitialSize(5);
        return bds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }


}
