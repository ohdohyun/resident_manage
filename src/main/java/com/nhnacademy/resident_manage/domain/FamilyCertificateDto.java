package com.nhnacademy.resident_manage.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface FamilyCertificateDto {

    String getName();

    String getGenderCode();

    LocalDateTime getBirthDate();

    String getRegistrationBaseAddress();

    String getResidentRegistrationNumber();

    Long getCertificateConfirmationNumber();

    LocalDate getCertificateIssueDate();

}
