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
class FamilyRelationshipRestControllerTest {

    @Autowired
    WebApplicationContext context;
    @Autowired
    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private final Long testBaseSerialNumber = 7L;
    private final Long testFamilySerialNumber = 4L;
    private final String testRelationship = "test용 관계";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }

    @Test
    @DisplayName("새로운 가족관계 정상 등록 테스트 7-1")
    void resister() throws Exception {
        Long tempSerialNumber = 1L;

        FamilyRelationRegister test = new FamilyRelationRegister();
        test.setRelationship(testRelationship);
        test.setFamilySerialNumber(tempSerialNumber);

        mockMvc.perform(post("/residents/" + testBaseSerialNumber + "/relationship")
                        .content(objectMapper.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseResidentSerialNumber").value(testBaseSerialNumber))
                .andExpect(jsonPath("$.familyResidentSerialNumber").value(tempSerialNumber))
                .andReturn();
    }
    @Test
    void update() throws Exception {
        FamilyRelationUpdate test = new FamilyRelationUpdate();
        test.setRelationship(testRelationship);
        mockMvc.perform(put("/residents/" + testBaseSerialNumber + "/relationship/" + testFamilySerialNumber)
                        .content(objectMapper.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updateRelationship").value(testRelationship))
                .andReturn();
    }

    @Test
    @DisplayName("정상 삭제 테스트")
    void deleteTest() throws Exception{
        mockMvc.perform(delete("/residents/" + testBaseSerialNumber + "/relationship/" + testFamilySerialNumber))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

}