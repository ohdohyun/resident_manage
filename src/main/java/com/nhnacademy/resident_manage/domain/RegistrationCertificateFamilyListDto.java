package com.nhnacademy.resident_manage.domain;

import java.time.LocalDate;

public interface RegistrationCertificateFamilyListDto {

    String getRelationship();

    String getName();

    String getRegistration();

    LocalDate getDate();

    String getReason();
}
