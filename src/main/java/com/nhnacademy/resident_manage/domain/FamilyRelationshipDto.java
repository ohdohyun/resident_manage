package com.nhnacademy.resident_manage.domain;

import com.nhnacademy.resident_manage.entity.FamilyRelationship;

public interface FamilyRelationshipDto {
    String getFamilyRelationshipCode();

    FamilyRelationship.Pk getPk();
}
