package com.nhnacademy.resident_manage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.domain.*;
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
class DeathRestControllerTest {
    @Autowired
    WebApplicationContext context;
    @Autowired
    ObjectMapper objectMapper;
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }

    private final Long testReportSerialNumber = 2L;
    private final Long testTargetSerialNumber = 1L;
    private final LocalDate testReportDate = LocalDate.now();
    private final String testBirthReportQualificationsCode = "testNotBirthOrDeath";
    private final String testEmailAddress = "test@ohdo.com";
    private final String testPhoneNumber = "010-test-tset";

    @Test
    @DisplayName("사망 신고 등록 테스트")
    void createTest() throws Exception {
        DeathRegister test = new DeathRegister();
        test.setTargetResidentSerialNumber(testTargetSerialNumber);
        test.setDeathReportDate(testReportDate);
        test.setDeathReportQualificationsCode(testBirthReportQualificationsCode);
        test.setEmailAddress(testEmailAddress);
        test.setPhoneNumber(testPhoneNumber);

        // 4번이 1번 사망신고 등록
        mockMvc.perform(post("/residents/" + 4 + "/death")
                        .content(objectMapper.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.birthDeathTypeCode").value("사망"))
                .andExpect(jsonPath("$.residentSerialNumber").value(testTargetSerialNumber))
                // 새로 출생 신고한 아기의 일련번호 리턴
                .andReturn();
    }

    @Test
    @DisplayName("1-2 사망 신고 수정 테스트")
    void updateTest() throws Exception {
        DeathUpdate test = new DeathUpdate();
        test.setDeathReportDate(testReportDate);
        test.setDeathReportQualificationsCode(testBirthReportQualificationsCode);
        test.setEmailAddress(testEmailAddress);
        test.setPhoneNumber(testPhoneNumber);

        mockMvc.perform(put("/residents/" + testReportSerialNumber + "/death/" + testTargetSerialNumber)
                        .content(objectMapper.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.birthDeathTypeCode").value("사망"))
                .andExpect(jsonPath("$.residentSerialNumber").value(testTargetSerialNumber))
                .andReturn();

    }

    @Test
    @DisplayName("1-2 사망 신고 삭제 테스트")
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/residents/" + testReportSerialNumber + "/death/" + testTargetSerialNumber))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}

