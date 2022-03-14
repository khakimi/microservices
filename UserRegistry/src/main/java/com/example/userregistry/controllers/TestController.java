package com.example.userregistry.controllers;

import com.example.userregistry.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    private  JwtUtil jwtUtil;

    @GetMapping("/test")
    public String test(HttpServletRequest httpServletRequest){
        return  jwtUtil.getUserPhoneNumberFromToken(httpServletRequest.getHeader("Authorization"));

    }
}
