package com.nhnacademy.resident_manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/residents")
public class ResidentController {

    @PostMapping("/")
    public void register() {


    }
}
