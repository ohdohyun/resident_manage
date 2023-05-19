package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.BirthRegister;
import com.nhnacademy.resident_manage.domain.BirthUpdate;
import com.nhnacademy.resident_manage.service.BirthDeathService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/birth")
@RequiredArgsConstructor
public class BirthRestController {

    private final BirthDeathService birthDeathService;

    @PostMapping
    public void register(@PathVariable Long serialNumber, @RequestBody BirthRegister birthRegister) {
        birthDeathService.birthSave(serialNumber, birthRegister);
    }

    @PutMapping("/{targetSerialNumber}")
    public void update(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber, @RequestBody BirthUpdate birthUpdate) {
        birthDeathService.birthUpdate(serialNumber,targetSerialNumber, birthUpdate);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public void delete(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber) {
        birthDeathService.delete(serialNumber, targetSerialNumber);
    }

}
