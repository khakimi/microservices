package com.example.userregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRegistryApplication.class, args);
    }

}
