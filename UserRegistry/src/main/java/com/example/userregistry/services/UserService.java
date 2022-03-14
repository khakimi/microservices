package com.example.userregistry.services;

import com.example.userregistry.convertor.UserConvertor;
import com.example.userregistry.models.User;
import com.example.userregistry.models.UserDTO;
import com.example.userregistry.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static final Logger log = LoggerFactory.getLogger(UserService.class);
    public final UserRepository userRepository;
    public final UserConvertor userConvertor;

    @Autowired
    public UserService(UserRepository userRepository, UserConvertor userConvertor){
        this.userRepository = userRepository;
        this.userConvertor = userConvertor;
    }

    public void saveUser(UserDTO userDTO){
        User userTemp = userConvertor.DTOtoEntity(userDTO);
        userRepository.findUserByPhoneNumber(userDTO.getPhoneNumber()).ifPresent(user ->  userTemp.setId(user.getId()));
        userRepository.save(userTemp);
    }
    public UserDTO getUser(String phoneNumber){
        return userConvertor.entityToDTO(userRepository.findUserByPhoneNumber(phoneNumber).orElse(userRepository.save(new User(phoneNumber))));
    }
}
