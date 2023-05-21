package com.nhnacademy.resident_manage.controller.rest_controller;

import com.nhnacademy.resident_manage.domain.ResidentRegister;
import com.nhnacademy.resident_manage.domain.ResidentUpdate;
import com.nhnacademy.resident_manage.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentRestController {
    private final ResidentService residentService;

    @PostMapping
    public Map<String, Long> register(@RequestBody ResidentRegister residentRegister) {
        Map<String, Long> result = new HashMap<>();
        result.put("id", residentService.save(residentRegister));
        return result;
    }

    @PutMapping("/{serialNumber}")
    public Map<String, Long> update(@PathVariable Long serialNumber, @RequestBody ResidentUpdate residentUpdate) {
        Map<String, Long> result = new HashMap<>();
        result.put("id", residentService.update(serialNumber, residentUpdate));
        return result;
    }

}
