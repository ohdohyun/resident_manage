package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.BirthDeathDto;
import com.nhnacademy.resident_manage.entity.BirthDeathReportResident;
import com.nhnacademy.resident_manage.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class DeathService {

    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    public void save(BirthDeathDto birthDeathDto) {
        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode("사망");

        pk.setResidentSerialNumber(birthDeathDto.getTargetSerialNumber());
        birthDeathReportResident.setPk(pk);

        birthDeathReportResident.setReportResident(residentRepository.findById(birthDeathDto.getReportResidentSerialNumber())
                .orElseThrow(() -> new EntityNotFoundException()));
        birthDeathReportResident.setBirthReportQualificationsCode(birthDeathDto.getBirthDeathReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(birthDeathDto.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(birthDeathDto.getPhoneNumber());

        birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    public void delete(Long serialNumber, Long targetSerialNumber) {
        birthDeathReportResidentRepository
                .deleteBirthDeathReportResidentByPk_ResidentSerialNumberAndReportResident_ResidentSerialNumber
                        (targetSerialNumber, serialNumber);
    }
}