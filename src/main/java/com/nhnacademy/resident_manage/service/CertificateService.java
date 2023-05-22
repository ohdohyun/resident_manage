package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.*;
import com.nhnacademy.resident_manage.entity.BirthDeathReportResident;
import com.nhnacademy.resident_manage.entity.CertificateIssue;
import com.nhnacademy.resident_manage.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CertificateService {
    private final ResidentRepository residentRepository;
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final CertificateIssueRepository certificateIssueRepository;


    /********** 가족관계증명서 **********/
    @Transactional(readOnly = false)
    public FamilyRelationshipCertificateDto getFamilyRelationshipCertificate(Long serialNumber) {
        // 증명서 리스트에 저장
        Long confirmationNumber = saveCertificateIssue(serialNumber, "가족관계증명서");
        // 주민 & 증명서 정보 레포지토리에서 가져오기
        return certificateIssueRepository.findAllByCertificateConfirmationNumber(confirmationNumber);
    }

    /********** 주민등록등본 **********/
    @Transactional(readOnly = false)
    public RegistrationCertificateDto getRegistrationCertificate(Long serialNumber) {
        // 증명서 저장
        saveCertificateIssue(serialNumber, "주민등록등본");
        // 증명서 정보 리턴
        return residentRepository.getRegistrationCertificateDto(serialNumber);
    }

    public List<RegistrationCertificateAddressListDto> getRegistrationCertificate_addressList(Long serialNumber) {
        // 주소 & 신고일 등 리턴
        return residentRepository.getRegistrationCertificate_addressList(serialNumber);
    }

    public List<RegistrationCertificateFamilyListDto> getRegistrationCertificate_familyList(Long serialNumber) {
        // 세대주와의 관계, 성명, 주민등록번호, 신고일, 변동사유
        return residentRepository.getRegistrationCertificate_familyList(serialNumber);
    }


    public Long saveCertificateIssue(Long serialNumber, String certificateTypeCode) {

        CertificateIssue certificateIssue = new CertificateIssue();
        certificateIssue.setCertificateIssueDate(LocalDate.now());
        certificateIssue.setCertificateTypeCode(certificateTypeCode);
        certificateIssue.setResident(residentRepository.getReferenceById(serialNumber));

        return certificateIssueRepository.saveAndFlush(certificateIssue).getCertificateConfirmationNumber();
    }

    /********** 출생신고서 **********/
    public BirthReportDto getBirthReportCertificate(Long serialNumber) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk("출생", serialNumber);

        return birthDeathReportResidentRepository.getBirthReportDtoByPk(pk);
    }

    /********** 사망신고서 **********/
    public DeathReportDto getDeathReportCertificate(Long serialNumber) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk("사망", serialNumber);
        DeathReportDto deathReportDto =birthDeathReportResidentRepository.getDeathReportDtoByPk(pk);
        return deathReportDto;
    }

    /********** 증명서 발급 목록 **********/
    public Page<CertificateIssue> getCertificateIssuePage(Long serialNumber, Pageable pageable) {
        return certificateIssueRepository.findAllByResident_ResidentSerialNumber(serialNumber, pageable);
    }

    public ParentDto getParent(Long serialNumber, String type) {
        return residentRepository.getParentByType(serialNumber, type);
    }


}
