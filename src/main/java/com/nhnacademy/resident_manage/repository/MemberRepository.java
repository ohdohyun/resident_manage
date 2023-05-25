package com.nhnacademy.resident_manage.repository;

import com.nhnacademy.resident_manage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
