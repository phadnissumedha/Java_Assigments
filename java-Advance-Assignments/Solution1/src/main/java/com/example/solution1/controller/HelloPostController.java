/*
Question 2 : Write an API “/hello” (POST method) to accept a name as an input, 
and return “Hello<name>” as an output. [API introduction & JSON data handling, 4 hours]
*/

package com.example.solution1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloPostController
{

    public static class NameRequest 
    {
        private String name;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    @PostMapping("/hello")
    public String sayHello(@RequestBody NameRequest request) {
        return "Hello " + request.getName();
    }
}

