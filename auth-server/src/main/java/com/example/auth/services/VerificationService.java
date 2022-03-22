package com.example.auth.services;

import com.example.auth.dto.VerificationResult;

public interface VerificationService {

    public VerificationResult startVerification(String phone);

    public VerificationResult checkVerification(String phone, String code);

}
