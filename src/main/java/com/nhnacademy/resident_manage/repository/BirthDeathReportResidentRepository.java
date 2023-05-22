package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.domain.BirthReportDto;
import com.nhnacademy.resident_manage.domain.DeathReportDto;
import com.nhnacademy.resident_manage.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {

    // 신고 주민 일련번호와 주민 일련번호로 찾기
    BirthDeathReportResident findByReportResident_ResidentSerialNumberAndResident_ResidentSerialNumber(Long serialNumber, Long targetSerialNumber);

    void deleteByReportResident_ResidentSerialNumberAndResident_ResidentSerialNumber(Long serialNumber, Long targetSerialNumber);

    BirthReportDto getBirthReportDtoByPk(BirthDeathReportResident.Pk pk);

    DeathReportDto getDeathReportDtoByPk(BirthDeathReportResident.Pk pk);
}
