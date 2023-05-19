package com.nhnacademy.resident_manage.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "household_movement_address")
@Getter
@Setter
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
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Pk implements Serializable {
        private Long householdSerialNumber;
        private LocalDate houseMovementReportDate;
    }

}
