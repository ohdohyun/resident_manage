package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
    // 출생사망신고 신고주민 일련번호와 타겟 일련번호로 삭제
    public void deleteBirthDeathReportResidentByPk_ResidentSerialNumberAndReportResident_ResidentSerialNumber(Long targetSerialNumber, Long serialNumber);
}
