package com.nhnacademy.resident_manage.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
public class BirthDeathDto {
    private Long targetSerialNumber;
    private Long reportResidentSerialNumber;
    private LocalDate birthDeathReportDate;
    private String birthDeathReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;
}
