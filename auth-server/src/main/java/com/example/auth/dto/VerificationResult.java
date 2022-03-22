package com.example.auth.dto;

import lombok.Data;

@Data
public class VerificationResult {

    private final String id;
    private final String[] errors;
    private final boolean valid;

    public VerificationResult(String id) {
        this.id = id;
        this.errors = new String[]{};
        this.valid = true;
    }

    public VerificationResult(String[] errors) {
        this.id = "";
        this.errors = errors;
        this.valid = false;
    }

}