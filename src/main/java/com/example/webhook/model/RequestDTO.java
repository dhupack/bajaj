package com.example.webhook.model;

public class RequestDTO {
    private String name;
    private String regNo;
    private String email;

    public RequestDTO(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    // Getters and Setters
}
