package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.domain.ResidentRegister;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@Transactional
class ResidentServiceTest {

    @Autowired
    private ResidentService residentService;

    @Test
    void save() {
        String testName = "test";
        String testRegistrationNumber = "123456-098765";
        String testGenderCode = "남";
        LocalDateTime testBirthDate = LocalDateTime.now();
        String testBirthPlaceCode = "자택";
        String testRegistrationBaseAddress = "대왕판교로645";

        ResidentRegister test = new ResidentRegister();
        test.setName(testName);
        test.setResidentRegistrationNumber(testRegistrationNumber);
        test.setGenderCode(testGenderCode);
        test.setBirthDate(testBirthDate);
        test.setBirthPlaceCode(testBirthPlaceCode);
        test.setRegistrationBaseAddress(testRegistrationBaseAddress);

        residentService.save(test);
    }

    @Test
    void update() {
    }
}