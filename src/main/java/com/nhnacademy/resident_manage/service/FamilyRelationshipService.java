package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.FamilyRelationRegister;
import com.nhnacademy.resident_manage.domain.FamilyRelationUpdate;
import com.nhnacademy.resident_manage.entity.FamilyRelationship;
import com.nhnacademy.resident_manage.entity.Resident;
import com.nhnacademy.resident_manage.repository.FamilyRelationshipRepository;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;
    public FamilyRelationship.Pk save(Long baseSerialNumber, FamilyRelationRegister familyRelationRegister) {
        Resident baseResident = residentRepository.findById(baseSerialNumber).orElseThrow();
        Resident familyResident = residentRepository.findById(familyRelationRegister.getFamilySerialNumber()).orElseThrow();

        FamilyRelationship familyRelationship = new FamilyRelationship();
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();

        pk.setFamilyResidentSerialNumber(familyRelationRegister.getFamilySerialNumber());
        pk.setBaseResidentSerialNumber(baseSerialNumber);
        familyRelationship.setPk(pk);

        familyRelationship.setFamilyRelationshipCode(familyRelationRegister.getRelationship());

        familyRelationship.setFamilyResident(familyResident);
        familyRelationship.setBaseResident(baseResident);

        return familyRelationshipRepository.save(familyRelationship).getPk();

    }
    public String update(Long baseSerialNumber, Long familySerialNumber, FamilyRelationUpdate familyRelationUpdate) {
        FamilyRelationship familyRelationship = familyRelationshipRepository.findById(new FamilyRelationship.Pk(baseSerialNumber, familySerialNumber)).orElseThrow();
        familyRelationship.setFamilyRelationshipCode(familyRelationUpdate.getRelationship());
        return familyRelationshipRepository.save(familyRelationship).getFamilyRelationshipCode();
    }

    public void delete(Long baseSerialNumber, Long familySerialNumber) {
        familyRelationshipRepository.deleteById(new FamilyRelationship.Pk(baseSerialNumber,familySerialNumber));
    }


}
