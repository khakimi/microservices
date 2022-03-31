package com.example.auth.controllers;

import com.example.auth.security.JwtUtil;
import com.example.auth.services.PhoneVerificationService;
import com.example.auth.dto.VerificationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

import static com.example.auth.security.SecurityConstants.OTP;
import static com.example.auth.security.SecurityConstants.PHONE_NUMBER;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")

public class AuthController {

    private final JwtUtil jwtUtil;

    private final PhoneVerificationService phoneVerificationService;

    @PostMapping("/test-login")
    public String testLogin(@RequestBody Map<String, String> phoneNumber) {
        return jwtUtil.generateToken(phoneNumber.get(PHONE_NUMBER));
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody Map<String, String> phoneNumber) {
        VerificationResult result = phoneVerificationService.startVerification(phoneNumber.get(PHONE_NUMBER));
        return result.isValid()
                ? new ResponseEntity<>("Otp was successfully sent on phone number: " + phoneNumber, HttpStatus.OK)
                : new ResponseEntity<>(Arrays.toString(result.getErrors()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> verification) {
        VerificationResult result = phoneVerificationService.checkVerification(verification.get(PHONE_NUMBER), verification.get(OTP));
        return result.isValid()
                ? new ResponseEntity<>(jwtUtil.generateToken(verification.get(PHONE_NUMBER)), HttpStatus.OK)
                : new ResponseEntity<>(Arrays.toString(result.getErrors()), HttpStatus.UNAUTHORIZED);
    }
}