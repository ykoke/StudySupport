package com.example.demo.common;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.annotation.PreDestroy;

@Configuration
public class DataSourceConfig {

    private HikariDataSource dataSource;

    @Bean
    public DataSource dataSource() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://b2764073ebfb4f:8b8f34e2@us-cluster-east-01.k8s.cleardb.net:3306/heroku_43f7f94d8b8c221?reconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8");
        config.setUsername("b2764073ebfb4f");
        config.setPassword("8b8f34e2");

        dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @PreDestroy
    public void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

}
