package com.nhnacademy.resident_manage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.domain.HouseholdMovementAddressRegister;
import com.nhnacademy.resident_manage.domain.HouseholdMovementAddressUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@Transactional
class HouseholdMovementRestControllerTest {

    @Autowired
    WebApplicationContext context;
    @Autowired
    ObjectMapper objectMapper;
    MockMvc mockMvc;

    private final Long testHouseholdSerialNumber = 1L;
    private final Long testHouseholdResidentSerialNumber = 1L;
    private final LocalDate testHouseMovementReportDate = LocalDate.of(2013, 3, 5);
    private final String testHouseholdMovementAddress = "대왕판교로 645 test";
    private final String testLastAddressYn = "NooooforTest";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }


    @Test
    @DisplayName("정상 등록 테스트")
    void register() throws Exception {
        HouseholdMovementAddressRegister test = new HouseholdMovementAddressRegister();
        test.setHouseholdResidentSerialNumber(testHouseholdResidentSerialNumber);
        test.setHouseMovementReportDate(testHouseMovementReportDate);
        test.setHouseholdMovementAddress(testHouseholdMovementAddress);
        test.setLastAddressYn(testLastAddressYn);

        mockMvc.perform(post("/household/" + testHouseholdSerialNumber + "/movement")
                        .content(objectMapper.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.householdSerialNumber").value(testHouseholdSerialNumber))
                .andExpect(jsonPath("$.houseMovementReportDate[0]").value(testHouseMovementReportDate.getYear()))
                .andExpect(jsonPath("$.houseMovementReportDate[1]").value(testHouseMovementReportDate.getMonthValue()))
                .andExpect(jsonPath("$.houseMovementReportDate[2]").value(testHouseMovementReportDate.getDayOfMonth()))

                .andReturn();
    }

    @Test
    @DisplayName("정상 수정 테스트")
    void update() throws Exception {
        HouseholdMovementAddressUpdate test = new HouseholdMovementAddressUpdate();
        test.setLastAddressYn(testLastAddressYn);

        mockMvc.perform(put("/household/" + testHouseholdSerialNumber + "/movement/" + testHouseMovementReportDate)
                        .content(objectMapper.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updateValue").value(testLastAddressYn))

                .andReturn();
    }

    @Test
    @DisplayName("정상 삭제 테스트")
    void deleteTest11() throws Exception {
        mockMvc.perform(delete("/household/" + testHouseholdSerialNumber + "/movement/" + testHouseMovementReportDate))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }
}