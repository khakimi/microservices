package com.example.auth.security;

import com.example.auth.twilio.PhoneVerificationService;
import com.example.auth.twilio.VerificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthRestController {


    private final JwtUtil jwtUtil;
    private final PhoneVerificationService phoneVerificationService;

    @Autowired
    public AuthRestController(JwtUtil jwtUtil,
                              PhoneVerificationService phoneVerificationService) {
        this.phoneVerificationService = phoneVerificationService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test()
    {
        return new ResponseEntity<>("test ok!",HttpStatus.OK);

    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> phoneNumber) {
        String token = jwtUtil.generateToken(phoneNumber.get("phoneNumber"));

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/sendOtp")
    public ResponseEntity<String> sendOtp(@RequestBody Map<String, String> phoneNumber)
    {
        VerificationResult result=phoneVerificationService.startVerification(phoneNumber.get("phoneNumber"));
        if(result.isValid())
        {
            return new ResponseEntity<>("Otp Sent..",HttpStatus.OK);
        }
        return new ResponseEntity<>(Arrays.toString(result.getErrors()),HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> verification)
    {
        VerificationResult result=phoneVerificationService.checkVerification(verification.get("phoneNumber"),verification.get("otp"));
        if(result.isValid())
        {
            String token = jwtUtil.generateToken(verification.get("phoneNumber"));
            return new ResponseEntity<>(token,HttpStatus.OK);
        }

        return new ResponseEntity<>("OTP is not valid", HttpStatus.UNAUTHORIZED);
    }


}