package com.example.userregistry.controllers;

import com.example.userregistry.dto.UserDTO;
import com.example.userregistry.security.JwtUtil;
import com.example.userregistry.security.SecurityConstants;
import com.example.userregistry.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.example.userregistry.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/save")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest) {
        String userPhoneNumber = getPhoneNumberFromHttpRequest(httpServletRequest);
        userDTO.setPhoneNumber(userPhoneNumber);
        userService.saveUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getUser(HttpServletRequest httpServletRequest) {
        String userPhoneNumber = getPhoneNumberFromHttpRequest(httpServletRequest);
        UserDTO userDTO = userService.getUser(userPhoneNumber);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    private String getPhoneNumberFromHttpRequest(HttpServletRequest httpServletRequest) {
        return jwtUtil.getUserPhoneNumberFromToken(httpServletRequest.getHeader(HEADER_STRING));
    }

}
