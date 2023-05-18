package com.nhnacademy.resident_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "resident")
public class Resident {
    @Id
    @Column(name = "resident_serial_number", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residentSerialNumber;
    @Column(nullable = false)
    private String name;
    @Column(name = "resident_registration_number", nullable = false)
    private String residentRegistrationNumber;
    @Column(name = "gender_code", nullable = false)
    private String genderCode;
    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;
    @Column(name = "birth_place_code", nullable = false)
    private String birthPlaceCode;
    @Column(name = "registration_base_address", nullable = false)
    private String registrationBaseAddress;
    @Column(name = "death_date")
    private LocalDateTime deathDate;
    @Column(name = "death_place_code")
    private String deathPlaceCode;
    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    @OneToMany(mappedBy = "baseResident")
    private List<FamilyRelationship> baseFamilyRelationships;

    @OneToMany(mappedBy = "familyResident")
    private List<FamilyRelationship> familyRelationships;

    @OneToMany(mappedBy = "resident")
    private List<BirthDeathReportResident> birthDeathResidents;

    @OneToMany(mappedBy = "reportResident")
    private List<BirthDeathReportResident> birthDeathReportResidents;

    @OneToMany(mappedBy = "resident")
    private List<Household> households;

    @OneToMany(mappedBy = "resident")
    private List<HouseholdCompositionResident> householdCompositionResidents;

}
