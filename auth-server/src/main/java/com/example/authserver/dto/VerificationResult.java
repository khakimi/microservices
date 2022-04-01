package com.example.authserver.dto;

import lombok.Data;

@Data
public class VerificationResult {

    private final String id;

    private final String[] errors;

    private final boolean isValid;

    public VerificationResult(String id) {
        this.id = id;
        this.errors = new String[]{};
        this.isValid = true;
    }

    public VerificationResult(String[] errors) {
        this.id = "";
        this.errors = errors;
        this.isValid = false;
    }

    public boolean isValid() {
        return isValid;
    }
}