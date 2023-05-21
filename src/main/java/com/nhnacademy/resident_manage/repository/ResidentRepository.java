package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.domain.FamilyCertificateDto;
import com.nhnacademy.resident_manage.domain.ResidentFamilyListDto;
import com.nhnacademy.resident_manage.domain.ResidentListDto;
import com.nhnacademy.resident_manage.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
    Page<ResidentListDto> getResidentsBy(Pageable pageable);

    @Query(value = "select ci.certificateTypeCode as certificateTypeCode, ci.certificateIssueDate as certificateIssueDate, ci.certificateConfirmationNumber as certificateConfirmationNumber, " +
            "r.registrationBaseAddress as registrationBaseAddress, r.name as name, r.birthDate as birthDate, r.residentRegistrationNumber as residentRegistrationNumber, r.genderCode as genderCode " +
            "from Resident r " +
            "join CertificateIssue ci on r.residentSerialNumber = ci.resident.residentSerialNumber " +
            "where r.residentSerialNumber = :serialNumber and ci.certificateTypeCode = '가족관계증명서' " +
            "and ci.certificateConfirmationNumber = 1234567891011121")
    FamilyCertificateDto getFamilyCertificateDto(@Param("serialNumber") Long serialNumber);

    @Query(value = "select r2.name as name, r2.birthDate as birthDate, r2.residentRegistrationNumber as residentRegistrationNumber, r2.genderCode as genderCode, " +
            "fr.familyRelationshipCode as familyRelationshipCode " +
            "from Resident r " +
            "join FamilyRelationship fr on r.residentSerialNumber = fr.pk.baseResidentSerialNumber " +
            "join Resident r2 on r2.residentSerialNumber = fr.pk.familyResidentSerialNumber " +
            "where r.residentSerialNumber = :serialNumber")
    List<ResidentFamilyListDto> getResidentFamilyList(@Param("serialNumber") Long serialNumber);
}
