package com.example.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {


    private JwtUtil jwtUtil;

    @Autowired
    public AuthRestController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody String userName) {
        String token = jwtUtil.generateToken(userName);

        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody String userName) {
        // Persist user to some persistent storage
        System.out.println("Info saved...");

        return new ResponseEntity<String>("Registered", HttpStatus.OK);
    }

}