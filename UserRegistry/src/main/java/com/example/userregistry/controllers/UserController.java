package com.example.userregistry.controllers;

import com.example.userregistry.models.UserDTO;
import com.example.userregistry.security.JwtUtil;
import com.example.userregistry.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/save")
    public ResponseEntity<Object> saveUser( @Valid @RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest){
        String userPhoneNumber = jwtUtil.getUserPhoneNumberFromToken(httpServletRequest.getHeader("Authorization"));
        if(userDTO.getPhoneNumber().equals(userPhoneNumber)){
            userService.saveUser(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/get")
    public ResponseEntity<Object> getUser(HttpServletRequest httpServletRequest){
        String userPhoneNumber = jwtUtil.getUserPhoneNumberFromToken(httpServletRequest.getHeader("Authorization"));
        UserDTO userDTO = userService.getUser(userPhoneNumber);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
