package com.example.auth.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

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
        assertEquals(true,
                jwtUtil.validateToken(
                        jwtUtil.generateToken("test")));
    }
}