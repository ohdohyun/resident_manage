package com.nhnacademy.resident_manage.controller.rest_controller;

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
    public Map<String, Long> register(@RequestBody HouseholdRegister householdRegister) {
        Map<String, Long> result = new HashMap<>();
        result.put("id", householdService.save(householdRegister));
        return result;
    }

    @DeleteMapping("/{householdSerialNumber}")
    public void delete(@PathVariable Long householdSerialNumber) {
        householdService.delete(householdSerialNumber);
    }


}
