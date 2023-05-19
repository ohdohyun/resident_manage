package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.domain.HouseholdRegister;
import com.nhnacademy.resident_manage.entity.Household;
import com.nhnacademy.resident_manage.repository.HouseholdRepository;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@Transactional
class HouseholdServiceTest {

    @Autowired
    private HouseholdService householdService;
    @Autowired
    private HouseholdRepository householdRepository;

    Long testHouseholdResidentSerialNumber = 4L;
    LocalDate testHouseholdCompositionDate = LocalDate.now();
    String testHouseholdCompositionReasonCode = "세대분리 test";
    String testCurrentHouseMovementAddress = "대왕판교로 645번길 test";

    @Test

    void save() {
        HouseholdRegister test = new HouseholdRegister();
        test.setCurrentHouseMovementAddress(testCurrentHouseMovementAddress);
        test.setHouseholdResidentSerialNumber(testHouseholdResidentSerialNumber);
        test.setHouseholdCompositionDate(testHouseholdCompositionDate);
        test.setHouseholdCompositionReasonCode(testHouseholdCompositionReasonCode);

        Assertions.assertThat(householdService.save(test)).isNotZero();
    }

    @Test
    void delete() {
        householdService.delete(1L);
        Assertions.assertThat(householdRepository.findById(1L)).isEmpty();
    }
}