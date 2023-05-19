package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.controller.HouseholdMovementRestController;
import com.nhnacademy.resident_manage.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {
}
