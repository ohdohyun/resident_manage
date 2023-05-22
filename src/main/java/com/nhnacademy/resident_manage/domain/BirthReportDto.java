package com.nhnacademy.resident_manage.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BirthReportDto {

    LocalDate getBirthDeathReportDate();

    String getBirthReportQualificationsCode();

    BirthReportDto.TargetResident getResident();

    BirthReportDto.ReportResident getReportResident();

    String getEmailAddress();

    String getPhoneNumber();

    interface TargetResident {

        String getName();

        String getGenderCode();

        LocalDateTime getBirthDate();

        String getBirthPlaceCode();

        String getRegistrationBaseAddress();
    }

    interface ReportResident {
        String getName();

        String getResidentRegistrationNumber();
    }

}
