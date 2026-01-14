package com.example.solution1.controller;

import com.example.solution1.dto.PropertyRequest;
import com.example.solution1.dto.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PropertyController {

    private final Environment environment;

    @Autowired
    public PropertyController(Environment environment) {
        this.environment = environment;
    }

    @PostMapping("/myproperties")
    public List<PropertyResponse> getProperties(@RequestBody PropertyRequest request) {
        List<PropertyResponse> responses = new ArrayList<>();

        for (String key : request.getPropertyKeys()) {
            String value = environment.getProperty(key);
            if (value == null) {
                value = "NULL";
            }
            responses.add(new PropertyResponse(key, value));
        }
        return responses;
    }
}
