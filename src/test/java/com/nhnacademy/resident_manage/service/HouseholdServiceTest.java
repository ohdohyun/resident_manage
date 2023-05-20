package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.domain.HouseholdRegister;
import com.nhnacademy.resident_manage.repository.HouseholdRepository;
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

import java.time.LocalDate;

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


    @Test
    @DisplayName("정상 등록 테스트")
    void save() {
        LocalDate testHouseholdCompositionDate = LocalDate.now();
        Long testHouseholdResidentSerialNumber = 4L;
        String testHouseholdCompositionReasonCode = "세대분리 test";
        String testCurrentHouseMovementAddress = "대왕판교로 645번길 test";

        HouseholdRegister test = new HouseholdRegister();
        test.setCurrentHouseMovementAddress(testCurrentHouseMovementAddress);
        test.setHouseholdResidentSerialNumber(testHouseholdResidentSerialNumber);
        test.setHouseholdCompositionDate(testHouseholdCompositionDate);
        test.setHouseholdCompositionReasonCode(testHouseholdCompositionReasonCode);

        Assertions.assertThat(householdService.save(test)).isNotZero();
    }

    @Test
    @DisplayName("정상 삭제 테스트")
    void delete() {
        householdService.delete(1L);
        Assertions.assertThat(householdRepository.findById(1L)).isEmpty();
    }
}