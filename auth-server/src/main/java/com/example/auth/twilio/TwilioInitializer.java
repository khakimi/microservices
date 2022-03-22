package com.example.auth.twilio;

import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TwilioInitializer {
    @Autowired
    public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
        Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());
        log.info("Twilio initialized with account-" + twilioConfiguration.getAccountSid());
    }

}