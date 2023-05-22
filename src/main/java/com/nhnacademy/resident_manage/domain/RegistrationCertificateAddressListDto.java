package com.nhnacademy.resident_manage.domain;

import java.time.LocalDate;

public interface RegistrationCertificateAddressListDto {
    String getYn();

    String getAddress();

    LocalDate getDate();
}
