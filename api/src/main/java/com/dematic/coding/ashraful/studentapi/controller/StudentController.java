package com.dematic.coding.ashraful.studentapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1.1")
public class StudentController {

    @GetMapping("/hello")
    public String sendGreetings() {
        return "Hello, World!";
    }

}
