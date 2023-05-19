package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.domain.FamilyRelationshipDto;
import com.nhnacademy.resident_manage.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {

    FamilyRelationshipDto findByPk(FamilyRelationship.Pk pk);


}
