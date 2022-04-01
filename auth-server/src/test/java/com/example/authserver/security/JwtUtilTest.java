package com.example.authserver.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setup(){
        jwtUtil = new JwtUtil();
    }

    @Test
    void generateTokenAndGetStringTest() {
        //when then
        assertEquals("test",
                jwtUtil.getUserPhoneNumberFromToken(
                        jwtUtil.generateToken("test")));
    }

    @Test
    void generateAndValidateTokenTest() {
        //when then
        assertTrue(jwtUtil.validateToken(
                jwtUtil.generateToken("test")));
    }
}