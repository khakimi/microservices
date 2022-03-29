package com.example.userregistry.controllers;

import com.example.userregistry.dto.UserDTO;
import com.example.userregistry.security.JwtUtil;
import com.example.userregistry.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.example.userregistry.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final JwtUtil jwtUtil;

    @PostMapping("/save")
    public UserDTO saveUser(@Valid @RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest) {
        String userPhoneNumber = getPhoneNumberFromHttpRequest(httpServletRequest);
        userDTO.setPhoneNumber(userPhoneNumber);
        userServiceImpl.saveUser(userDTO);
        return userDTO;
    }

    @GetMapping("/get")
    public UserDTO getUser(HttpServletRequest httpServletRequest) {
        String userPhoneNumber = getPhoneNumberFromHttpRequest(httpServletRequest);
        UserDTO userDTO = userServiceImpl.getUser(userPhoneNumber);
        return userDTO;
    }

    private String getPhoneNumberFromHttpRequest(HttpServletRequest httpServletRequest) {
        return jwtUtil.getUserPhoneNumberFromToken(httpServletRequest.getHeader(HEADER_STRING));
    }
}
