package com.nhnacademy.resident_manage.controller;

import com.nhnacademy.resident_manage.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/residents")
public class ResidentController {
    private final ResidentService residentService;

    @GetMapping
    public String getResidentList(Model model, Pageable pageable) {
        model.addAttribute("residents", residentService.residentListDtoPage(pageable));
        return "resident_list";
    }


}
