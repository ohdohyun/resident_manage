package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.service.CertificateService;
import lombok.RequiredArgsConstructor;
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
        model.addAttribute("familyList", certificateService.getFamilyList(serialNumber));
        return "/certificate/family_relationship";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model, @PathVariable Long serialNumber) {

        return "/certificate/registration";
    }

    @GetMapping("/birth")
    public String getBirth(Model model, @PathVariable Long serialNumber) {

        return "/certificate/birth";
    }

    @GetMapping("/death")
    public String getDeath(Model model, @PathVariable Long serialNumber) {

        return "/certificate/death";
    }

    @GetMapping("/list")
    public String getCertificateList(Model model, @PathVariable Long serialNumber) {

        return "/certificate/list";
    }



}
