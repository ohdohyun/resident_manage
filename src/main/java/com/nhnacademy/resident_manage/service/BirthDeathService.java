package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.BirthRegister;
import com.nhnacademy.resident_manage.domain.BirthUpdate;
import com.nhnacademy.resident_manage.domain.DeathRegister;
import com.nhnacademy.resident_manage.domain.DeathUpdate;
import com.nhnacademy.resident_manage.entity.BirthDeathReportResident;
import com.nhnacademy.resident_manage.entity.Resident;
import com.nhnacademy.resident_manage.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BirthDeathService {

    private final BirthDeathReportResidentRepository bdRepository;
    private final ResidentRepository residentRepository;

    public BirthDeathReportResident.Pk birthSave(Long serialNumber, BirthRegister birthRegister) {
        Resident reportResident = residentRepository.findById(serialNumber).orElseThrow();
        Resident targetResident = residentRepository.findById(birthRegister.getTargetResidentSerialNumber()).orElseThrow();

        BirthDeathReportResident resident = new BirthDeathReportResident();
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();

        pk.setBirthDeathTypeCode("출생");
        pk.setResidentSerialNumber(birthRegister.getTargetResidentSerialNumber());
        resident.setPk(pk);

        resident.setResident(targetResident);
        resident.setReportResident(reportResident);
        resident.setBirthDeathReportDate(birthRegister.getBirthReportDate());
        resident.setBirthReportQualificationsCode(birthRegister.getBirthReportQualificationsCode());
        resident.setEmailAddress(birthRegister.getEmailAddress());
        resident.setPhoneNumber(birthRegister.getPhoneNumber());

        return bdRepository.save(resident).getPk();
    }

    public BirthDeathReportResident.Pk birthUpdate(Long serialNumber, Long targetSerialNumber, BirthUpdate birthUpdate) {


        BirthDeathReportResident resident = bdRepository.findByReportResident_ResidentSerialNumberAndResident_ResidentSerialNumber(serialNumber,targetSerialNumber);

        resident.setBirthDeathReportDate(birthUpdate.getBirthReportDate());
        resident.setBirthReportQualificationsCode(birthUpdate.getBirthReportQualificationsCode());
        resident.setEmailAddress(birthUpdate.getEmailAddress());
        resident.setPhoneNumber(birthUpdate.getPhoneNumber());

        return bdRepository.save(resident).getPk();
    }

    public BirthDeathReportResident.Pk deathSave(Long serialNumber, DeathRegister deathRegister) {
        Resident reportResident = residentRepository.findById(serialNumber).orElseThrow();
        Resident targetResident = residentRepository.findById(deathRegister.getTargetResidentSerialNumber()).orElseThrow();

        BirthDeathReportResident resident = new BirthDeathReportResident();
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();

        pk.setBirthDeathTypeCode("사망");
        pk.setResidentSerialNumber(deathRegister.getTargetResidentSerialNumber());
        resident.setPk(pk);

        resident.setResident(targetResident);
        resident.setReportResident(reportResident);
        resident.setBirthDeathReportDate(deathRegister.getDeathReportDate());
        resident.setDeathReportQualificationsCode(deathRegister.getDeathReportQualificationsCode());
        resident.setEmailAddress(deathRegister.getEmailAddress());
        resident.setPhoneNumber(deathRegister.getPhoneNumber());

        return bdRepository.save(resident).getPk();
    }

    public BirthDeathReportResident.Pk deathUpdate(Long serialNumber, Long targetSerialNumber, DeathUpdate deathUpdate) {
        BirthDeathReportResident resident = bdRepository.findByReportResident_ResidentSerialNumberAndResident_ResidentSerialNumber(serialNumber,targetSerialNumber);

        resident.setBirthDeathReportDate(deathUpdate.getDeathReportDate());
        resident.setDeathReportQualificationsCode(deathUpdate.getDeathReportQualificationsCode());
        resident.setEmailAddress(deathUpdate.getEmailAddress());
        resident.setPhoneNumber(deathUpdate.getPhoneNumber());

        return bdRepository.save(resident).getPk();
    }

    public void delete(Long serialNumber, Long targetSerialNumber) {
        bdRepository.deleteByReportResident_ResidentSerialNumberAndResident_ResidentSerialNumber(serialNumber, targetSerialNumber);
    }




}
