package com.example.notificationserver.services;

import com.example.notificationserver.models.UserNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;

    public boolean sendNotification(UserNotification userNotification)  {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userNotification.getEmail());
        message.setSubject("Uncompleted profile form");
        message.setText("Your user form is uncompleted, if you don't complete it, it will be annulled within 30 days!");
        try{
            emailSender.send(message);
            log.info("Message was successfully send to: "+ userNotification.getEmail());
        }
        catch (MailException e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }


}
