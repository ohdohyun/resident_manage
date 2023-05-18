package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.BirthDeathDto;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import com.nhnacademy.resident_manage.service.DeathService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/death")
@RequiredArgsConstructor
public class DeathRestController {
    private final DeathService deathService;

    @PostMapping
    public void create(@PathVariable Long serialNumber, @RequestBody BirthDeathDto birthDeathDto) {
        birthDeathDto.setReportResidentSerialNumber(serialNumber);
        deathService.save(birthDeathDto);
    }

    @PutMapping("/{targetSerialNumber}")
    public void update(@PathVariable Long serialNumber,@PathVariable Long targetSerialNumber, @RequestBody BirthDeathDto birthDeathDto) {
        birthDeathDto.setReportResidentSerialNumber(serialNumber);
        birthDeathDto.setTargetSerialNumber(targetSerialNumber);
        deathService.save(birthDeathDto);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public void delete(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber) {
        deathService.delete(serialNumber, targetSerialNumber);
    }

}
