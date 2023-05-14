package com.nhnacademy.resident_manage.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class FamilyRelationship {

    @EmbeddedId
    private Pk pk;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number")
    private Resident baseResident;

    @MapsId("familyResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "family_resident_serial_number")
    private Resident familyResident;

    @Column(name = "family_relationship_code")
    private String FamilyRelationshipCode;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Pk implements Serializable {
        @Column(name = "base_resident_serial_number")
        private Long baseResidentSerialNumber;
        @Column(name = "family_resident_serial_number")
        private Long familyResidentSerialNumber;
    }
}
