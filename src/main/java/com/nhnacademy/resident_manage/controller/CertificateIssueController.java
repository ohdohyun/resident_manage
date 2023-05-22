package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.RegistrationCertificateDto;
import com.nhnacademy.resident_manage.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/certificate/{serialNumber}")
public class CertificateIssueController {

    private final CertificateService certificateService;

    @GetMapping("/familyRelationship")
    public String getFamilyRelationship(Model model, @PathVariable Long serialNumber) {

        model.addAttribute("familyCertificateInfo", certificateService.getFamilyRelationshipCertificate(serialNumber));
        return "/certificate/family_relationship";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model, @PathVariable Long serialNumber) {

        // 등본 정보
        RegistrationCertificateDto registrationInfo = certificateService.getRegistrationCertificate(serialNumber);
        model.addAttribute("registrationInfo", registrationInfo);
        // 본인 기준 주소 리스트
        model.addAttribute("registrationAddressList", certificateService.getRegistrationCertificate_addressList(serialNumber));
        // 세대주 관계, 성명 등 리스트
        model.addAttribute("registrationFamilyList", certificateService.getRegistrationCertificate_familyList(registrationInfo.getHrsn()));

        return "/certificate/registration";
    }

    @GetMapping("/birth")
    public String getBirth(Model model, @PathVariable Long serialNumber) {

        model.addAttribute("birthReport", certificateService.getBirthReportCertificate(serialNumber));
        model.addAttribute("father", certificateService.getParent(serialNumber, "부"));
        model.addAttribute("mother", certificateService.getParent(serialNumber, "모"));
        return "/certificate/birth";
    }

    @GetMapping("/death")
    public String getDeath(Model model, @PathVariable Long serialNumber) {
        model.addAttribute("deathReport", certificateService.getDeathReportCertificate(serialNumber));
        return "/certificate/death";
    }

    @GetMapping("/list")
    public String getCertificateList(Model model, @PathVariable Long serialNumber, Pageable pageable) {
        model.addAttribute("certificatePage", certificateService.getCertificateIssuePage(serialNumber, pageable));
        return "/certificate/list";
    }


}
