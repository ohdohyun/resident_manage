package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.HouseholdMovementDto;
import com.nhnacademy.resident_manage.entity.HouseholdMovementAddress;
import com.nhnacademy.resident_manage.repository.HouseholdMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class HouseholdMovementService {
    private final HouseholdMovementRepository householdMovementRepository;

    public HouseholdMovementAddress.Pk save(Long householdSerialNumber, HouseholdMovementDto householdMovementDto) {
        householdMovementDto.setHouseholdSerialNumber(householdSerialNumber);
        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();
        pk.setHouseholdSerialNumber(householdSerialNumber);
        pk.setHouseMovementReportDate(LocalDate.now());
        householdMovementAddress.setPk(pk);
        householdMovementAddress.setLastAddressYn(householdMovementDto.getLastAddressYn());
        householdMovementAddress.setHouseMovementAddress(householdMovementDto.getHouseMovementAddress());

        return householdMovementRepository.save(householdMovementAddress).getPk();
    }

    public Long update(Long householdSerialNumber, LocalDate reportDate, HouseholdMovementDto householdMovementDto) {
        householdMovementDto.setHouseholdSerialNumber(householdSerialNumber);
        householdMovementDto.setHouseholdMovementReportDate(reportDate);
        new HouseholdMovementAddress();
        return null;
    }

    public void delete(Long householdSerialNumber, LocalDate reportDate) {
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();
        pk.setHouseholdSerialNumber(householdSerialNumber);
        pk.setHouseMovementReportDate(reportDate);
        householdMovementRepository.deleteById(pk);
    }

}
