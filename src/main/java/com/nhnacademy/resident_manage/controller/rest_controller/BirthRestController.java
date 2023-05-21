package com.nhnacademy.resident_manage.controller.rest_controller;

import com.nhnacademy.resident_manage.domain.BirthRegister;
import com.nhnacademy.resident_manage.domain.BirthUpdate;
import com.nhnacademy.resident_manage.entity.BirthDeathReportResident;
import com.nhnacademy.resident_manage.service.BirthDeathService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/birth")
@RequiredArgsConstructor
public class BirthRestController {

    private final BirthDeathService birthDeathService;

    @PostMapping
    public BirthDeathReportResident.Pk register(@PathVariable Long serialNumber, @RequestBody BirthRegister birthRegister) {
        return birthDeathService.birthSave(serialNumber, birthRegister);
    }

    @PutMapping("/{targetSerialNumber}")
    public BirthDeathReportResident.Pk update(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber, @RequestBody BirthUpdate birthUpdate) {
        return birthDeathService.birthUpdate(serialNumber,targetSerialNumber, birthUpdate);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public void delete(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber) {
        birthDeathService.delete(serialNumber, targetSerialNumber);
    }

}
