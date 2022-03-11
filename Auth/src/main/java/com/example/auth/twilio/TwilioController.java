package com.example.auth.twilio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/otp")
@CrossOrigin
public class TwilioController {

    @Autowired
    PhoneVerificationService phoneVerificationService;



    @GetMapping("/test")
    public ResponseEntity<String> test()
    {
        return new ResponseEntity<>("test ok!",HttpStatus.OK);

    }


    @PostMapping("/sendOtp")
    public ResponseEntity<String> sendOtp(@RequestParam("phone") String phone)
    {
        VerificationResult result=phoneVerificationService.startVerification(phone);
        if(result.isValid())
        {
            return new ResponseEntity<>("Otp Sent..",HttpStatus.OK);
        }
        return new ResponseEntity<>(Arrays.toString(result.getErrors()),HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestParam("phone") String phone, @RequestParam("otp") String otp)
    {
        VerificationResult result=phoneVerificationService.checkVerification(phone,otp);
        if(result.isValid())
        {
            return new ResponseEntity<>("Your number is Verified",HttpStatus.OK);
        }
        return new ResponseEntity<>("Otp incorrect",HttpStatus.BAD_REQUEST);
    }


}