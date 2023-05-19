package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.FamilyRelationRegister;
import com.nhnacademy.resident_manage.domain.FamilyRelationUpdate;
import com.nhnacademy.resident_manage.service.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class FamilyRelationshipRestController {
    private final FamilyRelationshipService familyRelationshipService;

    @PostMapping("/{serialNumber}/relationship")
    public void resister(@PathVariable Long serialNumber, @RequestBody FamilyRelationRegister familyRelationRegister) {
        familyRelationshipService.save(serialNumber, familyRelationRegister);
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public void update(@PathVariable("serialNumber") Long serialNumber, @PathVariable("familySerialNumber") Long familySerialNumber, @RequestBody FamilyRelationUpdate familyRelationUpdate) {
        familyRelationshipService.update(serialNumber, familySerialNumber, familyRelationUpdate);
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber})")
    public void delete(@PathVariable Long serialNumber, @PathVariable Long familySerialNumber) {
        familyRelationshipService.delete(serialNumber, familySerialNumber);
    }

}
