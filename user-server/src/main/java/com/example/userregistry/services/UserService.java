package com.example.userregistry.services;

import com.example.userregistry.dto.UserDTO;

public interface UserService {

    public void saveUser(UserDTO userDTO);
    
    public UserDTO getUser(String phoneNumber);

}
