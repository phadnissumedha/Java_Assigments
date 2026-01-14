package com.example.solution1.dto;

public class PropertyResponse {

    private String key;
    private String value;

    // Constructor
    public PropertyResponse(String key, String value) {
        this.key = key;
        this.value = value;
    }

    // Getter for key
    /**
     * @return
     */
    public String getKey() {
        return key;
    }

    // Getter for value
    public String getValue() {
        return value;
    }
}
