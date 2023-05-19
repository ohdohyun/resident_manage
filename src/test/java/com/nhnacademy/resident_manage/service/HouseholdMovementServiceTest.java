package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.domain.HouseholdMovementAddressRegister;
import com.nhnacademy.resident_manage.domain.HouseholdMovementAddressUpdate;
import com.nhnacademy.resident_manage.entity.HouseholdMovementAddress;
import com.nhnacademy.resident_manage.repository.HouseholdMovementRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@Transactional
class HouseholdMovementServiceTest {
    @Autowired
    private HouseholdMovementService householdMovementService;
    @Autowired
    private HouseholdMovementRepository repository;
    private final Long testHouseholdSerialNumber = 1L;
    private final Long testHouseholdResidentSerialNumber= 1L;
    private final LocalDate testHouseMovementReportDate = LocalDate.of(2013,3,5);
    private final String testHouseholdMovementAddress = "대왕판교로 645 test";
    private final String testLastAddressYn = "NooooforTest";

    @Test
    @DisplayName("HouseMovementAddress 저장 테스트")
    void save() {
        HouseholdMovementAddressRegister test = new HouseholdMovementAddressRegister();
        test.setHouseholdResidentSerialNumber(testHouseholdResidentSerialNumber);
        test.setHouseMovementReportDate(testHouseMovementReportDate);
        test.setHouseholdMovementAddress(testHouseholdMovementAddress);
        test.setLastAddressYn(testLastAddressYn);

        HouseholdMovementAddress.Pk pk = householdMovementService.save(testHouseholdSerialNumber, test);

        Assertions.assertThat(pk).isNotNull();
        Assertions.assertThat(pk.getHouseholdSerialNumber()).isEqualTo(testHouseholdSerialNumber);
        Assertions.assertThat(pk.getHouseMovementReportDate()).isEqualTo(testHouseMovementReportDate);
    }

    @Test
    void update() {
        // 기존 1, 20130305 데이터를 Y -> Nooootest로 수정 후 확인
        HouseholdMovementAddressUpdate test = new HouseholdMovementAddressUpdate();
        test.setLastAddressYn(testLastAddressYn);

        Assertions.assertThat(householdMovementService
                        .update(testHouseholdSerialNumber, testHouseMovementReportDate, test))
                .isEqualTo(testLastAddressYn);
    }

    @Test
    void delete() {
        householdMovementService.delete(testHouseholdSerialNumber,testHouseMovementReportDate);
        Assertions.assertThat(repository.findById(
                new HouseholdMovementAddress.Pk(testHouseholdSerialNumber, testHouseMovementReportDate))).isEmpty();
    }
}