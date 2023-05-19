package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.domain.FamilyRelationRegister;
import com.nhnacademy.resident_manage.domain.FamilyRelationUpdate;
import com.nhnacademy.resident_manage.entity.FamilyRelationship;
import com.nhnacademy.resident_manage.repository.FamilyRelationshipRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@Transactional
class FamilyRelationshipServiceTest {

    @Autowired
    private FamilyRelationshipService familyRelationshipService;
    @Autowired
    private FamilyRelationshipRepository repository;

    private final Long testBaseSerialNumber = 1L;
    private final Long testFamilySerialNumber = 7L;
    private final String testRelationship = "test용 관계";

    @Test
    void save() {
        FamilyRelationRegister test = new FamilyRelationRegister();

        test.setRelationship(testRelationship);
        test.setFamilySerialNumber(testFamilySerialNumber);

        FamilyRelationship.Pk pk = familyRelationshipService.save(testBaseSerialNumber, test);

        Assertions.assertThat(pk).isNotNull();
        Assertions.assertThat(pk.getBaseResidentSerialNumber()).isEqualTo(testBaseSerialNumber);
        Assertions.assertThat(pk.getFamilyResidentSerialNumber()).isEqualTo(testFamilySerialNumber);
    }

    @Test
    void update() {
        FamilyRelationUpdate test = new FamilyRelationUpdate();
        test.setRelationship(testRelationship);
        Assertions.assertThat(familyRelationshipService.update(1L, 2L, test))
                .isEqualTo(testRelationship);
    }

    @Test
    void delete() {
        familyRelationshipService.delete(1L, 2L);
        repository.findById(new FamilyRelationship.Pk(1L, 2L)).isEmpty();
    }
}