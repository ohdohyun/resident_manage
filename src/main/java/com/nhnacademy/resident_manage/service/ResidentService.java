package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.ResidentRegister;
import com.nhnacademy.resident_manage.domain.ResidentUpdate;
import com.nhnacademy.resident_manage.entity.Resident;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResidentService {
    private final ResidentRepository residentRepository;

    public Long save(ResidentRegister residentRegister) {
        Resident resident = new Resident();
        resident.setName(residentRegister.getName());
        resident.setResidentRegistrationNumber(residentRegister.getResidentRegistrationNumber());
        resident.setGenderCode(residentRegister.getGenderCode());
        resident.setBirthDate(residentRegister.getBirthDate());
        resident.setBirthPlaceCode(residentRegister.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentRegister.getRegistrationBaseAddress());

        return residentRepository.save(resident).getResidentSerialNumber();
    }
    public Long update(Long serialNumber, ResidentUpdate residentUpdate) {
        Resident resident = residentRepository.getReferenceById(serialNumber);
        resident.setName(residentUpdate.getName());
        resident.setGenderCode(residentUpdate.getGenderCode());
        resident.setRegistrationBaseAddress(residentUpdate.getRegistrationBaseAddress());

        return residentRepository.save(resident).getResidentSerialNumber();
    }

}