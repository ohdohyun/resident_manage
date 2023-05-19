package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.HouseholdMovementAddressRegister;
import com.nhnacademy.resident_manage.domain.HouseholdMovementAddressUpdate;
import com.nhnacademy.resident_manage.entity.HouseholdMovementAddress;
import com.nhnacademy.resident_manage.service.HouseholdMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/household/{householdSerialNumber}/movement")
public class HouseholdMovementRestController {
    private final HouseholdMovementService householdMovementService;

    @PostMapping
    public void register(@PathVariable Long householdSerialNumber, @RequestBody HouseholdMovementAddressRegister householdMovementAddressRegister) {
        householdMovementService.save(householdSerialNumber, householdMovementAddressRegister);
    }

    @PutMapping("/{reportDate}")
    public void update(@PathVariable Long householdSerialNumber, @PathVariable LocalDate reportDate, @RequestBody HouseholdMovementAddressUpdate householdMovementAddressUpdate) {
        householdMovementService.update(householdSerialNumber, reportDate, householdMovementAddressUpdate);
    }

    public void delete(@PathVariable Long householdSerialNumber, @PathVariable LocalDate reportDate) {
        householdMovementService.delete(householdSerialNumber, reportDate);
    }
}
