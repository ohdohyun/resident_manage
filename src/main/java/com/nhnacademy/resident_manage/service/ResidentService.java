package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.entity.Resident;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ResidentService {
    private final ResidentRepository residentRepository;

    public void save(Resident resident) {
        residentRepository.save(resident);
    }

}
