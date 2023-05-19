package com.nhnacademy.resident_manage.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class HouseholdMovementDto {
    private LocalDate householdMovementReportDate;
    private Long householdSerialNumber;
    private String houseMovementAddress;
    private String lastAddressYn;

}
