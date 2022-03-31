package com.example.auth.twilio;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@Configuration
public class TwilioConfiguration {

    @Value("${twilio.account_sid:}")
    private String accountSid;

    @Value("${twilio.auth_token:}")
    private String authToken;

    @Value("${twilio.service_id:}")
    private String serviceId;

}