package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.ResidentRegister;
import com.nhnacademy.resident_manage.domain.ResidentUpdate;
import com.nhnacademy.resident_manage.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentRestController {
    private final ResidentService residentService;

    @PostMapping
    public void register(@RequestBody ResidentRegister residentRegister) {
        residentService.save(residentRegister);
    }

    @PutMapping("/{serialNumber}")
    public void update(@PathVariable Long serialNumber, @RequestBody ResidentUpdate residentUpdate) {
        residentService.update(serialNumber, residentUpdate);
    }

}
