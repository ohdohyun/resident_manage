package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.HouseholdMovementAddressRegister;
import com.nhnacademy.resident_manage.domain.HouseholdMovementAddressUpdate;
import com.nhnacademy.resident_manage.entity.Household;
import com.nhnacademy.resident_manage.entity.HouseholdMovementAddress;
import com.nhnacademy.resident_manage.entity.Resident;
import com.nhnacademy.resident_manage.repository.HouseholdMovementRepository;
import com.nhnacademy.resident_manage.repository.HouseholdRepository;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class HouseholdMovementService {
    private final HouseholdMovementRepository householdMovementRepository;
    private final HouseholdRepository householdRepository;
    public void save(Long householdSerialNumber, HouseholdMovementAddressRegister register) {
        Household household = householdRepository.getReferenceById(householdSerialNumber);

        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();

        pk.setHouseMovementReportDate(register.getHouseMovementReportDate());
        pk.setHouseholdSerialNumber(householdSerialNumber);
        householdMovementAddress.setPk(pk);

        householdMovementAddress.setHousehold(household);
        householdMovementAddress.setHouseMovementAddress(register.getHouseholdMovementAddress());
        householdMovementAddress.setLastAddressYn(register.getLastAddressYn());

        householdMovementRepository.save(householdMovementAddress);
    }

    public void update(Long householdSerialNumber, LocalDate reportDate, HouseholdMovementAddressUpdate update) {
        HouseholdMovementAddress householdMovementAddress = householdMovementRepository.getReferenceById(new HouseholdMovementAddress.Pk(householdSerialNumber, reportDate));
        householdMovementAddress.setLastAddressYn(update.getLastAddressYn());
        householdMovementRepository.save(householdMovementAddress);
    }

    public void delete(Long householdSerialNumber, LocalDate reportDate) {
        householdMovementRepository.deleteById(new HouseholdMovementAddress.Pk(householdSerialNumber,reportDate));
    }

}
