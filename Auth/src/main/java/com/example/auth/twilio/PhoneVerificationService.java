package com.example.auth.twilio;

import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneVerificationService {

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public PhoneVerificationService(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration=twilioConfiguration;
    }


    public VerificationResult startVerification(String phone) {
        try {
            Verification verification = Verification.creator(twilioConfiguration.getServiceId(), phone, "sms").create();
            if(verification.getStatus().equals("approved")|| verification.getStatus().equals("pending")) {
                return new VerificationResult(verification.getSid());
            }
        } catch (ApiException exception) {
            return new VerificationResult(new String[] {exception.getMessage()});
        }
        return null;
    }

    public VerificationResult checkVerification(String phone, String code) {
        try {
            VerificationCheck verification = VerificationCheck.creator(twilioConfiguration.getServiceId(), code).setTo(phone).create();
            if(verification.getStatus().equals("approved")) {
                return new VerificationResult(verification.getSid());
            }
            return new VerificationResult(new String[]{"Invalid code."});
        } catch (ApiException exception) {
            return new VerificationResult(new String[]{exception.getMessage()});
        }
    }



}