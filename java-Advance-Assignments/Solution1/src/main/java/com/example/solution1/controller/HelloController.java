/*
Question1 : Write an API “/hello” (GET method) to return “Hello World” as output. [SpringBoot Main, 2
hours] */

package com.example.solution1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
