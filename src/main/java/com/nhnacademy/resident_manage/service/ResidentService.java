package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.ResidentDto;
import com.nhnacademy.resident_manage.entity.Resident;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ResidentService {
    private final ResidentRepository residentRepository;

    public Long save(ResidentDto residentDto) {
        return residentRepository.saveAndFlush(residentDto.toEntity()).getResidentSerialNumber();
    }
    public Long update(ResidentDto residentDto, Long id) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " not found"));

        return residentRepository.save(resident).getResidentSerialNumber();
    }

}