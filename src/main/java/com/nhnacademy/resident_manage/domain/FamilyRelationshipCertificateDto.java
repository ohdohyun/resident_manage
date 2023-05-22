package com.nhnacademy.resident_manage.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FamilyRelationshipCertificateDto {

    MainResident getResident();

    Long getCertificateConfirmationNumber();

    LocalDate getCertificateIssueDate();

    interface MainResident {

        String getRegistrationBaseAddress();

        String getName();

        LocalDateTime getBirthDate();

        String getResidentRegistrationNumber();
        String getGenderCode();


        List<FamilyResidentRelationships> getBaseFamilyRelationships();

        interface FamilyResidentRelationships {
            String getFamilyRelationshipCode();

            FamilyResident getFamilyResident();

            interface FamilyResident {
                String getName();

                LocalDateTime getBirthDate();

                String getResidentRegistrationNumber();

                String getGenderCode();
            }
        }
    }
}
