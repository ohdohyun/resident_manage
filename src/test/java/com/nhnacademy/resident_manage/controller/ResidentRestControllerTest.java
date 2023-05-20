package com.nhnacademy.resident_manage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.domain.ResidentRegister;
import com.nhnacademy.resident_manage.domain.ResidentUpdate;
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

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
class ResidentRestControllerTest {

    @Autowired
    WebApplicationContext context;
    @Autowired
    ObjectMapper objectMapper;
    MockMvc mockMvc;

    /**
     * 테스트용 데이터 입니다
     */
    private final String testName = "testUpdate";
    private final String testGenderCode = "남Update";
    private final LocalDateTime testBirthDate = LocalDateTime.now();
    private final String testRegistrationBaseAddress = "대왕판교로645Test";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }

    @Test
    @DisplayName("BirthRestControllerTest 등록 test")
    void create() throws Exception {
        String testRegistrationNumber = "123456-098765";
        String testBirthPlaceCode = "자택";

        ResidentRegister test = new ResidentRegister();
        test.setName(testName);
        test.setResidentRegistrationNumber(testRegistrationNumber);
        test.setGenderCode(testGenderCode);
        test.setBirthDate(testBirthDate);
        test.setBirthPlaceCode(testBirthPlaceCode);
        test.setRegistrationBaseAddress(testRegistrationBaseAddress);


        mockMvc.perform(post("/residents")
                        .content(objectMapper.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andReturn();
    }

    @Test
    void update() throws Exception {
        ResidentUpdate test = new ResidentUpdate();
        test.setName(testName);
        test.setGenderCode(testGenderCode);
        test.setRegistrationBaseAddress(testRegistrationBaseAddress);

        mockMvc.perform(put("/residents/2")
                        .content(objectMapper.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andReturn();
    }

}