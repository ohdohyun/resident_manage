package com.nhnacademy.resident_manage.config;

import com.nhnacademy.resident_manage.Base;
import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackageClasses = {Base.class}, excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {

    private final DatabaseProperties properties;

    @Bean
    public DataSource dataSource(){
        BasicDataSource DataSource = new BasicDataSource();
        DataSource.setDriverClassName(properties.getDriverClassName());
        DataSource.setUrl(properties.getUrl());
        DataSource.setUsername(properties.getUsername());
        DataSource.setPassword(properties.getPassword());
        DataSource.setInitialSize(properties.getInitialSize());
        DataSource.setMaxTotal(properties.getMaxTotal());
        DataSource.setMaxIdle(properties.getMaxIdle());
        DataSource.setMinIdle(properties.getMinIdle());
        DataSource.setMaxWaitMillis(properties.getMaxWaitMillis());
        DataSource.setValidationQuery(properties.getValidationQuery());
        DataSource.setTestOnBorrow(properties.isTestOnBorrow());
        if(properties.isTestOnBorrow()) {
            DataSource.setValidationQuery(DataSource.getValidationQuery());
        }
        return DataSource;
    }

}
