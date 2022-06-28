package com.study.code_spb_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String[] hello() {
        return new String[]{"hi", "World"};
    }
}
