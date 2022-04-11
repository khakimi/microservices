package com.example.userserver.services;

import com.example.userserver.models.UserNotification;
import com.example.userserver.repository.UserRepository;
import com.example.userserver.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class NotificationScheduleService {

    private final UserRepository userRepository;

    private final long WEEK_IN_MS = 1000 * 60 * 60 * 24 * 7;

    private final JwtUtil jwtUtil;


    @Scheduled(cron = "${schedule.delay}")
    public void checkIfNeedToSendNotification() {
        List<UserNotification> userNotifications
                = userRepository.findUsersByLastChangeDateBefore(new Date(System.currentTimeMillis() - (WEEK_IN_MS)))
                .stream()
                .map(u -> new UserNotification(u.getName(), u.getEmail(), u.getPhoneNumber()))
                .collect(Collectors.toList());
        if (!userNotifications.isEmpty()) {
            sendNotificationRequest(userNotifications);
        }
    }

    private void sendNotificationRequest(List<UserNotification> userNotifications){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(""));
        HttpEntity<List<UserNotification>> entity = new HttpEntity<>(userNotifications, headers);
        HttpEntity<String> response = restTemplate.exchange(
                "http://localhost:9094/api/v1/notification/send",
                HttpMethod.POST,
                entity,
                String.class);
        log.info(response.toString());
    }
}

