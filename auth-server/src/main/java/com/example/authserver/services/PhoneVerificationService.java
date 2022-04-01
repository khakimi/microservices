package com.example.authserver.services;

import com.example.authserver.dto.VerificationResult;
import com.example.authserver.twilio.TwilioConfiguration;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PhoneVerificationService implements VerificationService {

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public PhoneVerificationService(TwilioConfiguration twilioConfiguration){
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());
        log.info("Twilio initialized with account-" + twilioConfiguration.getAccountSid());
    }

    public VerificationResult startVerification(String phone) {
        try {
            Verification verification = Verification.creator(twilioConfiguration.getServiceId(), phone, "sms").create();
            log.info(phone + " : " + verification.getStatus());
            return (verification.getStatus().equals("approved") || verification.getStatus().equals("pending"))
                    ? new VerificationResult(verification.getSid()) : null;
        } catch (ApiException exception) {
            return new VerificationResult(new String[]{exception.getMessage()});
        }
    }

    public VerificationResult checkVerification(String phone, String code) {
        try {
            VerificationCheck verification = VerificationCheck.creator(twilioConfiguration.getServiceId(), code).setTo(phone).create();
            return verification.getStatus().equals("approved")
                    ? new VerificationResult(verification.getSid())
                    : new VerificationResult(new String[]{"Invalid code"});
        } catch (ApiException exception) {
            return new VerificationResult(new String[]{exception.getMessage()});
        }
    }
}