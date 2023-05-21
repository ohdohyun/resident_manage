package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.FamilyCertificateDto;
import com.nhnacademy.resident_manage.domain.ResidentFamilyListDto;
import com.nhnacademy.resident_manage.entity.CertificateIssue;
import com.nhnacademy.resident_manage.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificateService {
    private final ResidentRepository residentRepository;
    private final CertificateIssueRepository certificateIssueRepository;

    // 가족 관계 증명서
    public FamilyCertificateDto getFamilyRelationshipCertificate(Long serialNumber) {
        // 증명서 리스트에 저장
        saveCertificateIssue(serialNumber, "가족관계증명서");
        // 주민 & 증명서 정보 레포지토리에서 가져오기
        return residentRepository.getFamilyCertificateDto(serialNumber);
    }

    public List<ResidentFamilyListDto> getFamilyList(Long serialNumber) {
        // 가족 리스트 가져오기
        return residentRepository.getResidentFamilyList(serialNumber);
    }

    public Long saveCertificateIssue(Long serialNumber, String certificateTypeCode) {

        CertificateIssue certificateIssue = new CertificateIssue();
        certificateIssue.setCertificateIssueDate(LocalDate.now());
        certificateIssue.setCertificateTypeCode(certificateTypeCode);
        certificateIssue.setResident(residentRepository.getReferenceById(serialNumber));

        return certificateIssueRepository.save(certificateIssue).getCertificateConfirmationNumber();
    }
}
