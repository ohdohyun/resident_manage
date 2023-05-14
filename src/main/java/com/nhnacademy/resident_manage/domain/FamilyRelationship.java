package com.nhnacademy.resident_manage.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class FamilyRelationship {

    @EmbeddedId
    private Pk pk;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number")
    private Resident resident;

    @Column(name = "family_relationship_code")
    private String FamilyRelationshipCode;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Pk implements Serializable {
        @Column(name = "family_resident_serial_number")
        private Long FamilyResidentSerialNumber;
        @Column(name = "base_resident_serial_number")
        private Long residentSerialNumber;
    }
}
