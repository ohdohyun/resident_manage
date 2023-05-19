package com.nhnacademy.resident_manage.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HouseholdMovementAddressRegister {
    private Long householdResidentSerialNumber;
    private LocalDate houseMovementReportDate;
    private String householdMovementAddress;
    private String lastAddressYn;
}
