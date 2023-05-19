package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.HouseholdDto;
import com.nhnacademy.resident_manage.entity.Household;
import com.nhnacademy.resident_manage.repository.HouseholdRepository;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
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
    private final ResidentRepository residentRepository;

    @PostMapping
    public Map<String, Long> register(HouseholdDto householdDto) {
        Map<String, Long> result = new HashMap<>();
        result.put("id", householdService.save(householdDto));

        return result;
    }

    @DeleteMapping("/{householdSerialNumber}")
    public void delete(@PathVariable Long householdSerialNumber) {
        householdService.delete(householdSerialNumber);
    }


}
