package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.HouseholdMovementDto;
import com.nhnacademy.resident_manage.entity.HouseholdMovementAddress;
import com.nhnacademy.resident_manage.repository.HouseholdMovementRepository;
import com.nhnacademy.resident_manage.service.HouseholdMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/household/{householdSerialNumber}/movement")
public class HouseholdMovementRestController {
    private final HouseholdMovementService householdMovementService;

    @PostMapping
    public HouseholdMovementAddress.Pk register(@PathVariable Long householdSerialNumber, @RequestBody HouseholdMovementDto householdMovementDto) {
        return householdMovementService.save(householdSerialNumber, householdMovementDto);
    }

    @PutMapping("/{reportDate}")
    public void update(@PathVariable Long householdSerialNumber, @PathVariable LocalDate reportDate, @RequestBody HouseholdMovementDto householdMovementDto) {
        householdMovementService.update(householdSerialNumber, reportDate, householdMovementDto);
    }

    public void delete(@PathVariable Long householdSerialNumber, @PathVariable LocalDate reportDate) {
        householdMovementService.delete(householdSerialNumber, reportDate);
    }
}
