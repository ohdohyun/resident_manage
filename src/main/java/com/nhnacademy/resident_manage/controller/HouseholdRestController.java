package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.HouseholdRegister;
import com.nhnacademy.resident_manage.service.HouseholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/household")
public class HouseholdRestController {
    private final HouseholdService householdService;

    @PostMapping
    public void register(@RequestBody HouseholdRegister householdRegister) {
        householdService.save(householdRegister);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public void delete(@PathVariable Long householdSerialNumber) {
        householdService.delete(householdSerialNumber);
    }


}
