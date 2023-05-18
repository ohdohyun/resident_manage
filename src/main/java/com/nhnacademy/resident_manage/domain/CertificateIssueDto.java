package com.nhnacademy.resident_manage.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CertificateIssueDto {
    String certificateTypeCode;
    LocalDate certificateIssueDate;
}
