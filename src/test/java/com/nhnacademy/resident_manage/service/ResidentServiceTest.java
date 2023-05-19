package com.nhnacademy.resident_manage.service;

import com.nhnacademy.resident_manage.domain.ResidentDto;
import com.nhnacademy.resident_manage.entity.Resident;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResidentServiceTest {

    @Test
    void save() {
        ResidentRepository residentRepository1 = mock(ResidentRepository.class);

        when(residentRepository1.save(new Resident())).thenReturn(new Resident());

    }

    @Test
    void update() {
    }
}