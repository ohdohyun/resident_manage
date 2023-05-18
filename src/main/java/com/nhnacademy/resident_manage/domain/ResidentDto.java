package com.nhnacademy.resident_manage.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.resident_manage.entity.Resident;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResidentDto {
    String name;
    String residentRegistrationNumber;
    @NotNull
    String genderCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    LocalDateTime birthDate;
    String birthPlaceCode;
    String registrationBaseAddress;

    public Resident toEntity() {
        Resident resident = new Resident();
        resident.setName(this.name);
        resident.setResidentRegistrationNumber(this.residentRegistrationNumber);
        resident.setGenderCode(this.genderCode);
        resident.setBirthDate(this.birthDate);
        resident.setBirthPlaceCode(this.birthPlaceCode);
        resident.setRegistrationBaseAddress(this.registrationBaseAddress);

        return resident;
    }
}
