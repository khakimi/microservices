package com.example.auth.services;

import com.example.auth.dto.VerificationResult;

public interface VerificationService {

    VerificationResult startVerification(String phone);

    VerificationResult checkVerification(String phone, String code);

}
