package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.domain.FamilyRelationshipBaseId;
import com.nhnacademy.resident_manage.domain.FamilyRelationshipDto;
import com.nhnacademy.resident_manage.domain.FamilyRelationshipRelationOnly;
import com.nhnacademy.resident_manage.service.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class FamilyRelationshipRestController {
    private final FamilyRelationshipService familyRelationshipService;

    @PostMapping("/{serialNumber}/relationship")
    public FamilyRelationshipBaseId resister(@PathVariable Long serialNumber, @RequestBody FamilyRelationshipDto familyRelationshipDto) {
        return new FamilyRelationshipBaseId(familyRelationshipService.save(serialNumber, familyRelationshipDto));
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public void delete(@PathVariable("serialNumber") Long serialNumber, @PathVariable("familySerialNumber") Long familySerialNumber) {
        familyRelationshipService.delete(serialNumber, familySerialNumber);
    }

    @PutMapping("/{serialNumber}/relationship/{familySerialNumber})")
    public FamilyRelationshipBaseId update(@PathVariable Long serialNumber, @PathVariable Long familySerialNumber, @RequestBody FamilyRelationshipRelationOnly relationship) {
        return new FamilyRelationshipBaseId(familyRelationshipService.update(serialNumber, familySerialNumber, relationship));
    }

}
