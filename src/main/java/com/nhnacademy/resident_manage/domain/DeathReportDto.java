package com.nhnacademy.resident_manage.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DeathReportDto {
    LocalDate getBirthDeathReportDate();
    DeathReportDto.TargetResident getResident();
    DeathReportDto.ReportResident getReportResident();
    String getBirthReportQualificationsCode();

    String getEmailAddress();

    String getPhoneNumber();

    interface TargetResident {

        String getName();

        String getGenderCode();

        String getResidentRegistrationNumber();

        LocalDateTime getDeathDate();

        String getDeathPlaceCode();

        String getDeathPlaceAddress();

    }
    interface ReportResident {
        String getName();

        String getResidentRegistrationNumber();
    }


}