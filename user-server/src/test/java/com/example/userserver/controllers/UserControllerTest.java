package com.example.userserver.controllers;

import com.example.userserver.converter.UserMapper;
import com.example.userserver.models.User;
import com.example.userserver.repository.UserRepository;
import com.example.userserver.security.JwtUtil;
import com.example.userserver.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.example.userserver.security.SecurityConstants.HEADER_STRING;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        userRepository.deleteAll();
    }

    @Test
    void saveUser() throws Exception {
        //given
        String phoneNumber = "1234567";
        User user = new User(phoneNumber);
        //when then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user))
                        .header(HEADER_STRING, jwtUtil.generateToken(phoneNumber)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber));

    }

    @Test
    void getUser() throws Exception {
        //given
        String phoneNumber = "1234567";
        //when then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/get")
                        .header(HEADER_STRING, jwtUtil.generateToken(phoneNumber))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}