package com.nhnacademy.resident_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "household")
@Getter
@Setter
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number")
    private Long householdSerialNumber;
    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;
    @Column(name = "household_composition_date")
    private LocalDate householdCompositionDate;
    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;
    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;

    @OneToMany(mappedBy = "household")
    private List<HouseholdCompositionResident> householdCompositionResidents;

    @OneToMany(mappedBy = "household")
    private List<HouseholdMovementAddress> householdMovementAddresses;
}
