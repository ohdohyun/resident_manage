package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.domain.ResidentRegister;
import com.nhnacademy.resident_manage.domain.ResidentUpdate;
import com.nhnacademy.resident_manage.entity.Resident;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import org.assertj.core.api.Assertions;
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
    @Autowired
    private ResidentRepository residentRepository;

    String testName = "testUpdate";
    String testRegistrationNumber = "123456-098765";
    String testGenderCode = "남Update";
    LocalDateTime testBirthDate = LocalDateTime.now();
    String testBirthPlaceCode = "자택";
    String testRegistrationBaseAddress = "대왕판교로645Test";

    @Test
    void save() {
        ResidentRegister test = new ResidentRegister();
        test.setName(testName);
        test.setResidentRegistrationNumber(testRegistrationNumber);
        test.setGenderCode(testGenderCode);
        test.setBirthDate(testBirthDate);
        test.setBirthPlaceCode(testBirthPlaceCode);
        test.setRegistrationBaseAddress(testRegistrationBaseAddress);

        Assertions.assertThat(residentService.save(test)).isNotZero();
        Long result1 = residentService.save(test);

        ResidentRegister test2 = new ResidentRegister();
        test2.setName(testName);
        test2.setResidentRegistrationNumber(testRegistrationNumber);
        test2.setGenderCode(testGenderCode);
        test2.setBirthDate(testBirthDate);
        test2.setBirthPlaceCode(testBirthPlaceCode);
        test2.setRegistrationBaseAddress(testRegistrationBaseAddress);
        Long result2 = residentService.save(test2);

        // 아이디 증가 확인
        Assertions.assertThat(result1).isNotEqualTo(result2);
    }

    @Test
    void update() {
        ResidentUpdate test = new ResidentUpdate();
        test.setName(testName);
        test.setGenderCode(testGenderCode);
        test.setRegistrationBaseAddress(testRegistrationBaseAddress);
        residentService.update(1L, test);

        Resident result1 = residentRepository.getReferenceById(1L);
        Assertions.assertThat(result1.getName()).isEqualTo(testName);
        Assertions.assertThat(result1.getGenderCode()).isEqualTo(testGenderCode);
        Assertions.assertThat(result1.getRegistrationBaseAddress()).isEqualTo(testRegistrationBaseAddress);

    }
}