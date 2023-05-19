package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.config.RootConfig;
import com.nhnacademy.resident_manage.config.WebConfig;
import com.nhnacademy.resident_manage.domain.FamilyRelationshipDto;
import com.nhnacademy.resident_manage.entity.FamilyRelationship;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@Transactional
class FamilyRelationshipRepositoryTest {

    @Autowired
    private FamilyRelationshipRepository familyRelationshipRepository;

    @Test
    @Disabled
    @DisplayName("FamilyRelationship 저장 테스트")
    void save() {
        Long testFamilySerialNumber = 1L;
        Long testSerialNumber = 2L;
        String testRelationShip = "test관계";

        FamilyRelationship familyRelationship = new FamilyRelationship();
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();

        pk.setBaseResidentSerialNumber(testSerialNumber);
        pk.setFamilyResidentSerialNumber(testFamilySerialNumber);
        familyRelationship.setPk(pk);
        familyRelationship.setFamilyRelationshipCode(testRelationShip);

        FamilyRelationship testFamilyRelationship = familyRelationshipRepository.save(familyRelationship);

        Assertions.assertThat(testFamilyRelationship.getPk().getFamilyResidentSerialNumber()).isEqualTo(testFamilySerialNumber);
        Assertions.assertThat(testFamilyRelationship.getPk().getBaseResidentSerialNumber()).isEqualTo(testSerialNumber);
        Assertions.assertThat(testFamilyRelationship.getFamilyRelationshipCode()).isEqualTo(testRelationShip);
    }

    @Test
    @DisplayName("DTO interface test")
    void test11() {
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();
        pk.setBaseResidentSerialNumber(1L);
        pk.setFamilyResidentSerialNumber(2L);

        FamilyRelationshipDto relationshipDto = familyRelationshipRepository.findByPk(pk);
        FamilyRelationship.Pk resultPk = relationshipDto.getPk();
        String rst = relationshipDto.getFamilyRelationshipCode();
        System.out.println("rst = " + rst);

        Assertions.assertThat(rst).isNotNull();
        Assertions.assertThat(resultPk).isNotNull();
    }

}