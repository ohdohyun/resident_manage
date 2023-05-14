package com.nhnacademy.resident_manage.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {

    @EmbeddedId
    private Pk pk;
    @ManyToOne
    @MapsId("householdSerialNumber")
    @JoinColumn(name = "household_serial_number")
    private Household household;
    private String houseMovementAddress;
    private String lastAddressYn;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Pk implements Serializable {
        private LocalDate houseMovementReportDate;
        private Long householdSerialNumber;
    }

}
