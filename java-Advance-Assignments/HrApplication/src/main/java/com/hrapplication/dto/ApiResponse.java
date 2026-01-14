package com.hrapplication.dto;

public class ApiResponse {

    private String status;
    private String errorCode;
    private String errorMessage;

    public ApiResponse(String status) {
        this.status = status;
    }

    public ApiResponse(String status, String errorCode, String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    // Getters and Setters
}
