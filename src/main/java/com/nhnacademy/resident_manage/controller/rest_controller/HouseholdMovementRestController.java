package com.nhnacademy.resident_manage.controller.rest_controller;

import com.nhnacademy.resident_manage.domain.HouseholdMovementAddressRegister;
import com.nhnacademy.resident_manage.domain.HouseholdMovementAddressUpdate;
import com.nhnacademy.resident_manage.entity.HouseholdMovementAddress;
import com.nhnacademy.resident_manage.service.HouseholdMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    public HouseholdMovementAddress.Pk register(@PathVariable Long householdSerialNumber, @RequestBody HouseholdMovementAddressRegister householdMovementAddressRegister) {
        return householdMovementService.save(householdSerialNumber, householdMovementAddressRegister);
    }

    @PutMapping("/{reportDate}")
    public Map<String, String> update(@PathVariable Long householdSerialNumber, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reportDate, @RequestBody HouseholdMovementAddressUpdate householdMovementAddressUpdate) {
        Map<String, String> result = new HashMap<>();
        result.put("updateValue", householdMovementService.update(householdSerialNumber, reportDate, householdMovementAddressUpdate));
        return result;
    }

    @DeleteMapping("/{reportDate}")
    public void delete(@PathVariable Long householdSerialNumber, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reportDate) {
        householdMovementService.delete(householdSerialNumber, reportDate);
    }
}
