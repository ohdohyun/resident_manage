package com.nhnacademy.resident_manage.domain;

import java.time.LocalDateTime;

public interface ResidentFamilyListDto {
    String getName();
    String getFamilyRelationshipCode();
    LocalDateTime getBirthDate();
    String getResidentRegistrationNumber();
    String getGenderCode();
}
