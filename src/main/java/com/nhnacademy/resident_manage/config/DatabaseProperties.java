package com.nhnacademy.resident_manage.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:db.properties")
@Setter
@Getter
public class DatabaseProperties {
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.driverClassName}")
    private String driverClassName;
    @Value("${db.url}")
    private String url;
    @Value("${db.initialSize}")
    private Integer initialSize;
    @Value("${db.maxTotal}")
    private Integer maxTotal;
    @Value("${db.maxIdle}")
    private Integer maxIdle;
    @Value("${db.minIdle}")
    private Integer minIdle;
    @Value("${db.maxWaitMillis}")
    private Integer maxWaitMillis;
    @Value("${db.validationQuery}")
    private String validationQuery;
    @Value("${db.testOnBorrow}")
    private Boolean testOnBorrow;

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }
}