package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.domain.BirthRegister;
import com.nhnacademy.resident_manage.domain.BirthUpdate;
import com.nhnacademy.resident_manage.domain.DeathRegister;
import com.nhnacademy.resident_manage.domain.DeathUpdate;
import com.nhnacademy.resident_manage.entity.BirthDeathReportResident;
import com.nhnacademy.resident_manage.repository.BirthDeathReportResidentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@Transactional
class BirthDeathServiceTest {

    @Autowired
    private BirthDeathService bdService;
    @Autowired
    private BirthDeathReportResidentRepository repository;

    private final Long testReportSerialNumber = 7L;
    private final Long testTargetSerialNumber = 3L;
    private final LocalDate testReportDate = LocalDate.now();
    private final String testBirthReportQualificationsCode = "testNotBirthOrDeath";
    private final String testDeathReportQualificationsCode = "testNotBirthOrDeath";
    private final String testEmailAddress = "test@ohdo.com";
    private final String testPhoneNumber = "010-test-tset";


    @Test
    void birthSave() {
        BirthRegister test = new BirthRegister();
        test.setTargetResidentSerialNumber(testTargetSerialNumber);
        test.setBirthReportDate(testReportDate);
        test.setBirthReportQualificationsCode(testBirthReportQualificationsCode);
        test.setEmailAddress(testEmailAddress);
        test.setPhoneNumber(testPhoneNumber);

        BirthDeathReportResident.Pk pk = bdService.birthSave(testReportSerialNumber, test);
        Assertions.assertThat(pk).isNotNull();
        Assertions.assertThat(pk.getBirthDeathTypeCode()).isEqualTo("birth");
        Assertions.assertThat(pk.getResidentSerialNumber()).isEqualTo(testTargetSerialNumber);
    }

    @Test
    void birthUpdate() {
        birthSave();

        BirthUpdate test = new BirthUpdate();
        test.setBirthReportDate(testReportDate);
        test.setBirthReportQualificationsCode(testBirthReportQualificationsCode);
        test.setEmailAddress(testEmailAddress);
        test.setPhoneNumber(testPhoneNumber);

        BirthDeathReportResident.Pk pk = bdService.birthUpdate(testReportSerialNumber, testTargetSerialNumber, test);
        Assertions.assertThat(pk).isNotNull();
        Assertions.assertThat(pk.getResidentSerialNumber()).isEqualTo(testTargetSerialNumber);
        Assertions.assertThat(pk.getBirthDeathTypeCode()).isEqualTo("birth");
    }

    @Test
    void deathSave() {
        DeathRegister test = new DeathRegister();
        test.setDeathReportDate(testReportDate);
        test.setDeathReportQualificationsCode(testDeathReportQualificationsCode);
        test.setTargetResidentSerialNumber(testTargetSerialNumber);
        test.setEmailAddress(testEmailAddress);
        test.setPhoneNumber(testPhoneNumber);

        BirthDeathReportResident.Pk pk = bdService.deathSave(testReportSerialNumber, test);

        Assertions.assertThat(pk).isNotNull();
        Assertions.assertThat(pk.getResidentSerialNumber()).isEqualTo(testTargetSerialNumber);
        Assertions.assertThat(pk.getBirthDeathTypeCode()).isEqualTo("death");

    }

    @Test
    void deathUpdate() {
        deathSave();

        DeathUpdate test = new DeathUpdate();
        test.setDeathReportDate(testReportDate);
        test.setDeathReportQualificationsCode(testBirthReportQualificationsCode);
        test.setEmailAddress(testEmailAddress);
        test.setPhoneNumber(testPhoneNumber);

        BirthDeathReportResident.Pk pk = bdService.deathUpdate(testReportSerialNumber, testTargetSerialNumber, test);
        Assertions.assertThat(pk).isNotNull();
        Assertions.assertThat(pk.getResidentSerialNumber()).isEqualTo(testTargetSerialNumber);
        Assertions.assertThat(pk.getBirthDeathTypeCode()).isEqualTo("death");
    }

    @Test
    void delete() {
        bdService.delete(testReportSerialNumber, testTargetSerialNumber);
        Assertions.assertThat(repository.findByReportResident_ResidentSerialNumberAndResident_ResidentSerialNumber(testReportSerialNumber, testTargetSerialNumber)).isNull();

    }

}