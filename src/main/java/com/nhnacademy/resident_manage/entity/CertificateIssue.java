package com.nhnacademy.resident_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificate_issue")
@Getter
@Setter
public class CertificateIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificateConfirmationNumber;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
    private String certificateTypeCode;
    private LocalDate certificateIssueDate;
}
