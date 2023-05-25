package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.domain.FamilyRelationshipCertificateDto;
import com.nhnacademy.resident_manage.entity.CertificateIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long > {
    Page<CertificateIssue> findAllByResident_ResidentSerialNumber(Long serialNumber, Pageable pageable);

    FamilyRelationshipCertificateDto findFamilyRelationshipCertificateByCertificateConfirmationNumber(Long certificateConfirmationNumber);

}
