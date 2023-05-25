package com.nhnacademy.resident_manage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Members")
@Getter
@Setter
public class Member {
    @Id
    @Column(name = "member_id")
    private String id;
    private String name;
    private String pwd;

    @OneToOne(mappedBy = "member", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Authority authority;
}
