package com.nhnacademy.resident_manage.domain;

import java.time.LocalDate;

public interface RegistrationCertificateDto {
    LocalDate getCertificateIssueDate();

    Long getCertificateConfirmationNumber();

    String getName();

    String getResidentRegistrationNumber();

    String getHouseholdCompositionReasonCode();

    LocalDate getHouseholdCompositionDate();

    Long getHrsn();

}
