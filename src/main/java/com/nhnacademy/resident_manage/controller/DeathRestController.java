package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.DeathRegister;
import com.nhnacademy.resident_manage.domain.DeathUpdate;
import com.nhnacademy.resident_manage.service.BirthDeathService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/death")
@RequiredArgsConstructor
public class DeathRestController {
    private final BirthDeathService birthDeathService;

    @PostMapping
    public void register(@PathVariable Long serialNumber, @RequestBody DeathRegister deathRegister) {
        birthDeathService.deathSave(serialNumber, deathRegister);
    }

    @PutMapping("/{targetSerialNumber}")
    public void update(@PathVariable Long serialNumber,@PathVariable Long targetSerialNumber, @RequestBody DeathUpdate deathUpdate) {
        birthDeathService.deathUpdate(serialNumber, targetSerialNumber, deathUpdate);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public void delete(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber) {
        birthDeathService.delete(serialNumber, targetSerialNumber);
    }

}
