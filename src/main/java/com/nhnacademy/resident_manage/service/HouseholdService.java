package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.HouseholdDto;
import com.nhnacademy.resident_manage.entity.Household;
import com.nhnacademy.resident_manage.repository.HouseholdRepository;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseholdService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;
    public Long save(HouseholdDto householdDto) {
        Household household = new Household();
        household.setResident(residentRepository.findById(householdDto.getHouseholdResidentSerialNumber())
                .orElseThrow());
        household.setHouseholdCompositionDate(householdDto.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(householdDto.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(householdDto.getCurrentHouseMovementAddress());

        return householdRepository.save(household).getHouseholdSerialNumber();
    }

    public void delete(Long householdSerialNumber) {
        householdRepository.deleteById(householdSerialNumber);
    }
}
