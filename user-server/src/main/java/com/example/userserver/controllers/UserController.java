package com.example.userserver.controllers;

import com.example.userserver.dto.UserDTO;
import com.example.userserver.security.JwtUtil;
import com.example.userserver.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import static com.example.userserver.security.SecurityConstants.HEADER_STRING;

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
        return userServiceImpl.getUser(userPhoneNumber);
    }

    private String getPhoneNumberFromHttpRequest(HttpServletRequest httpServletRequest) {
        return jwtUtil.getUserPhoneNumberFromToken(httpServletRequest.getHeader(HEADER_STRING));
    }
}