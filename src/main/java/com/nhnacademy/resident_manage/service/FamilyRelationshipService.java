package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.FamilyRelationshipDto;
import com.nhnacademy.resident_manage.domain.FamilyRelationshipRelationOnly;
import com.nhnacademy.resident_manage.entity.FamilyRelationship;
import com.nhnacademy.resident_manage.repository.FamilyRelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;

    public Long save(Long baseSerialNumber, FamilyRelationshipDto familyRelationshipDto) {
        FamilyRelationship familyRelationship = new FamilyRelationship();
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(baseSerialNumber, familyRelationshipDto.getFamilySerialNumber());
        familyRelationship.setPk(pk);
        familyRelationship.setFamilyRelationshipCode(familyRelationshipDto.getRelationship());

        return familyRelationshipRepository.save(familyRelationship).getPk().getBaseResidentSerialNumber();
    }

    public void delete(Long baseSerialNumber, Long familySerialNumber) {
        familyRelationshipRepository.deleteById(new FamilyRelationship.Pk(baseSerialNumber,familySerialNumber));
    }

    public Long update(Long baseSerialNumber, Long familySerialNumber, FamilyRelationshipRelationOnly relation) {
        FamilyRelationship familyRelationship = familyRelationshipRepository.findById(new FamilyRelationship.Pk(baseSerialNumber, familySerialNumber))
                .orElseThrow(() -> new EntityNotFoundException());
        familyRelationship.setFamilyRelationshipCode(relation.getRelationship());
        return familyRelationshipRepository.save(familyRelationship).getPk().getBaseResidentSerialNumber();
    }

}
