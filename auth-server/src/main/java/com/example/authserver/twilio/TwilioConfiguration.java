package com.example.authserver.twilio;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class TwilioConfiguration {

    public String getAccountSid() {
        return System.getenv("SID");
    }

    public String getAuthToken() {
        return System.getenv("TOKEN");
    }

    public String getServiceId() {
        return System.getenv("ID");
    }

}