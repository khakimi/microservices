package com.example.userregistry.controllers;

import com.example.userregistry.security.JwtUtil;
import com.example.userregistry.security.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

import static com.example.userregistry.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class TestController {

    private final JwtUtil jwtUtil;

    @GetMapping("/test")
    public String test(HttpServletRequest httpServletRequest) {
        return jwtUtil.getUserPhoneNumberFromToken(httpServletRequest.getHeader(HEADER_STRING));
    }

}
