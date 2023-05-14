package com.nhnacademy.resident_manage.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "household")
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long householdSerialNumber;
    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;
    private LocalDate householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;

    @OneToMany(mappedBy = "household")
    private List<HouseholdCompositionResident> householdCompositionResidents;

    @OneToMany(mappedBy = "household")
    private List<HouseholdMovementAddress> householdMovementAddresses;
}
