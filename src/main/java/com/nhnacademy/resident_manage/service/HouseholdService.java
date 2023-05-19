package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.HouseholdRegister;
import com.nhnacademy.resident_manage.entity.Household;
import com.nhnacademy.resident_manage.entity.Resident;
import com.nhnacademy.resident_manage.repository.HouseholdRepository;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseholdService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;
    public void save(HouseholdRegister householdRegister) {
        Resident householdResident = residentRepository.getReferenceById(householdRegister.getHouseholdResidentSerialNumber());
        Household household = new Household();

        household.setResident(householdResident);
        household.setHouseholdCompositionDate(householdRegister.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(household.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(household.getCurrentHouseMovementAddress());
        householdRepository.save(household);
    }

    public void delete(Long householdSerialNumber) {
        householdRepository.deleteById(householdSerialNumber);
    }
}
