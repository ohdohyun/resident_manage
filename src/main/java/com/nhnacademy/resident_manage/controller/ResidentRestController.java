package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.ResidentId;
import com.nhnacademy.resident_manage.domain.ResidentDto;
import com.nhnacademy.resident_manage.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentRestController {
    private final ResidentService residentService;

    @PostMapping
    public ResidentId create(@RequestBody ResidentDto resisterDto) {
        return new ResidentId(residentService.save(resisterDto));
    }

    @PutMapping("/{serialNumber}")
    public ResidentId updateResident(@PathVariable Long serialNumber, @RequestBody ResidentDto resisterDto) {
        return new ResidentId(residentService.update(resisterDto, serialNumber));
    }

}
