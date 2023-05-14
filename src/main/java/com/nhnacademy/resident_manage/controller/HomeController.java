package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.entity.Resident;
import com.nhnacademy.resident_manage.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    // TODO #1 테스트용 repository 삭제
    private final ResidentRepository residentRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("greeting", "hello??");
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model) {
        Optional<Resident> resident = residentRepository.findById(1L);
        model.addAttribute("resident", resident.get());
        return "test";
    }
}
