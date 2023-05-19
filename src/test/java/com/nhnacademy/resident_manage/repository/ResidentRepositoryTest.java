package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.entity.Resident;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@Transactional
class ResidentRepositoryTest {

    @Autowired
    private ResidentRepository residentRepository;

    @Test
    @DisplayName("repository save test")
    void save() {
        String testName = "test";
        String testRegistrationNumber = "123456-098765";
        String testGenderCode = "남";
        LocalDateTime testBirthDate = LocalDateTime.now();
        String testBirthPlaceCode = "자택";
        String testRegistrationBaseAddress = "대왕판교로645";

        Resident test = new Resident();
        test.setName(testName);
        test.setResidentRegistrationNumber(testRegistrationNumber);
        test.setGenderCode(testGenderCode);
        test.setBirthDate(testBirthDate);
        test.setBirthPlaceCode(testBirthPlaceCode);
        test.setRegistrationBaseAddress(testRegistrationBaseAddress);

        Resident test2 = new Resident();
        test2.setName(testName);
        test2.setResidentRegistrationNumber(testRegistrationNumber);
        test2.setGenderCode(testGenderCode);
        test2.setBirthDate(testBirthDate);
        test2.setBirthPlaceCode(testBirthPlaceCode);
        test2.setRegistrationBaseAddress(testRegistrationBaseAddress);

        Resident test3 = new Resident();
        test3.setName(testName);
        test3.setResidentRegistrationNumber(testRegistrationNumber);
        test3.setGenderCode(testGenderCode);
        test3.setBirthDate(testBirthDate);
        test3.setBirthPlaceCode(testBirthPlaceCode);
        test3.setRegistrationBaseAddress(testRegistrationBaseAddress);

        // save 성공 체크, Id 리턴 체크
        Assertions.assertThat(residentRepository.save(test)).isNotNull();
        Assertions.assertThat(residentRepository.save(test).getResidentSerialNumber()).isNotZero();

        // 아이디 증가 체크
        Resident result2 = residentRepository.save(test2);
        Resident result3 = residentRepository.save(test3);
        Assertions.assertThat(result2.getResidentSerialNumber()).isNotEqualTo(result3.getResidentSerialNumber());

    }

}