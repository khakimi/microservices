package com.example.notificationserver.controllers;

import com.example.notificationserver.models.UserNotification;
import com.example.notificationserver.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final EmailService emailService;
    @PostMapping("/send")
    public String sendNotification(@RequestBody List<UserNotification> userNotifications) {
        for (UserNotification userNotification
                : userNotifications) {
            if (userNotification.getEmail() != ""
                    && userNotification.getEmail() != null) {
                emailService.sendNotification(userNotification);
            }
        }
        return userNotifications.toString();
    }
}
