package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.BirthDeathDto;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import com.nhnacademy.resident_manage.service.BirthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/birth")
@RequiredArgsConstructor
public class BirthRestController {

    private final BirthService birthService;

    @PostMapping
    public void create(@PathVariable Long serialNumber, @RequestBody BirthDeathDto birthDeathDto) {
        birthDeathDto.setReportResidentSerialNumber(serialNumber);
        birthService.save(birthDeathDto);
    }

    @PutMapping("/{targetSerialNumber}")
    public void update(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber, @RequestBody BirthDeathDto birthDeathDto) {
        birthDeathDto.setReportResidentSerialNumber(serialNumber);
        birthDeathDto.setTargetSerialNumber(targetSerialNumber);
        birthService.save(birthDeathDto);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public void delete(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber) {
        birthService.delete(serialNumber, targetSerialNumber);
    }

}
