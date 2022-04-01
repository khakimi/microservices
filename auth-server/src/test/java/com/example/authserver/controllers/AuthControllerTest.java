package com.example.authserver.controllers;

import com.example.authserver.dto.VerificationResult;
import com.example.authserver.security.JwtUtil;
import com.example.authserver.services.PhoneVerificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static com.example.authserver.security.SecurityConstants.OTP;
import static com.example.authserver.security.SecurityConstants.PHONE_NUMBER;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PhoneVerificationService phoneVerificationService;

    private AuthController authController;

    @Autowired
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        authController = new AuthController(jwtUtil, phoneVerificationService);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void sendOtp() throws Exception {
        //given
        String phoneNumber = "1234567";
        Mockito.when(phoneVerificationService.startVerification(phoneNumber)).thenReturn(new VerificationResult("1"));
        //when then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth//send-otp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(Map.of(PHONE_NUMBER, phoneNumber))))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Otp was successfully sent on phone number: ")));
    }

    @Test
    void verifyOtp() throws Exception {
        //given
        String phoneNumber = "1234567";
        String otp = "123";
        Mockito.when(phoneVerificationService.checkVerification(phoneNumber, otp)).thenReturn(new VerificationResult("1"));
        //when then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/verify-otp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(Map.of(PHONE_NUMBER, phoneNumber, OTP, otp))))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}