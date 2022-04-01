package com.example.authserver.services;

import com.example.authserver.dto.VerificationResult;

public interface VerificationService {

    VerificationResult startVerification(String phone);

    VerificationResult checkVerification(String phone, String code);

}
