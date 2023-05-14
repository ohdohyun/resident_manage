package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.domain.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

}
