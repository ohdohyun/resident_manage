package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.domain.*;
import com.nhnacademy.resident_manage.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
    Page<ResidentListDto> getResidentsBy(Pageable pageable);

    /********** 주민등록등본 **********/
    @Query(value = "select ci.certificate_issue_date as certificateIssueDate, " +
            "ci.certificate_confirmation_number as certificateConfirmationNumber, " +
            "r2.name as name, h.household_resident_serial_number as hrsn, " +
            "r.resident_registration_number as residentRegistrationNumber, " +
            "h.household_composition_reason_code as householdCompositionReasonCode, " +
            "h.household_composition_date as householdCompositionDate " +
            "from resident r " +
            "join household_composition_resident hcr on r.resident_serial_number = hcr.resident_serial_number " +
            "join household h on h.household_serial_number = hcr.household_serial_number " +
            "join resident r2 on r2.resident_serial_number = h.household_resident_serial_number " +
            "join certificate_issue ci on r2.resident_serial_number = ci.resident_serial_number " +
            "where r.resident_serial_number = :serialNumber " +
            "order by ci.certificate_confirmation_number desc limit 1;" , nativeQuery = true)
    RegistrationCertificateDto getRegistrationCertificateDto(@Param("serialNumber") Long serialNumber);

    @Query(value = "select hma.last_address_yn as yn, hma.house_movement_address as address, hma.house_movement_report_date as date " +
            "from resident r " +
            "join household_composition_resident hcr on r.resident_serial_number = hcr.resident_serial_number " +
            "join household h on h.household_serial_number = hcr.household_serial_number " +
            "join resident r2 on r2.resident_serial_number = h.household_resident_serial_number " +
            "join household_movement_address hma on h.household_serial_number = hma.household_serial_number " +
            "where r.resident_serial_number = :serialNumber " +
            "order by yn desc", nativeQuery = true)
    List<RegistrationCertificateAddressListDto> getRegistrationCertificate_addressList(@Param("serialNumber") Long serialNumber);

    @Query(value = "select hcr.household_relationship_code as relationship, " +
            "r2.name as name, r2.resident_registration_number registration, " +
            "hcr.report_date as date, hcr.household_composition_change_reason_code as reason " +
            "from resident r " +
            "join household h on r.resident_serial_number = h.household_resident_serial_number " +
            "join household_composition_resident hcr on hcr.household_serial_number = h.household_serial_number " +
            "join resident r2 on hcr.resident_serial_number = r2.resident_serial_number " +
            "where r.resident_serial_number = :serialNumber ", nativeQuery = true)
    List<RegistrationCertificateFamilyListDto> getRegistrationCertificate_familyList(@Param("serialNumber") Long serialNumber);


    @Query(value = "select r.name as name, r.resident_registration_number as registration " +
            "from resident r " +
            "join family_relationship fr on fr.family_resident_serial_number = r.resident_serial_number " +
            "join resident r2 on r2.resident_serial_number = fr.base_resident_serial_number " +
            "where r2.resident_serial_number = :serialNumber and fr.family_relationship_code = :type", nativeQuery = true)
    ParentDto getParentByType(@Param("serialNumber") Long serialNumber, @Param("type") String type);
}
