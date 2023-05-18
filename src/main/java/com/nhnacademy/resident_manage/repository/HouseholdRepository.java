package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
}
