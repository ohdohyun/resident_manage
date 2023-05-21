package com.nhnacademy.resident_manage.controller.rest_controller;

import com.nhnacademy.resident_manage.domain.FamilyRelationRegister;
import com.nhnacademy.resident_manage.domain.FamilyRelationUpdate;
import com.nhnacademy.resident_manage.entity.FamilyRelationship;
import com.nhnacademy.resident_manage.service.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class FamilyRelationshipRestController {
    private final FamilyRelationshipService familyRelationshipService;

    @PostMapping("/{serialNumber}/relationship")
    public FamilyRelationship.Pk resister(@PathVariable Long serialNumber, @RequestBody FamilyRelationRegister familyRelationRegister) {
        return familyRelationshipService.save(serialNumber, familyRelationRegister);
    }

    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public Map<String ,String > update(@PathVariable("serialNumber") Long serialNumber, @PathVariable("familySerialNumber") Long familySerialNumber, @RequestBody FamilyRelationUpdate familyRelationUpdate) {
        Map<String, String> result = new HashMap<>();
        result.put("updateRelationship", familyRelationshipService.update(serialNumber, familySerialNumber, familyRelationUpdate));
        return result;
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public void delete(@PathVariable Long serialNumber, @PathVariable Long familySerialNumber) {
        familyRelationshipService.delete(serialNumber, familySerialNumber);
    }

}
