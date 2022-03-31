package com.example.userserver.services;

import com.example.userserver.dto.UserDTO;

public interface UserService {

    void saveUser(UserDTO userDTO);
    UserDTO getUser(String phoneNumber);

}
