package com.example.userserver.services;

import com.example.userserver.converter.UserMapper;
import com.example.userserver.dto.UserDTO;
import com.example.userserver.models.User;
import com.example.userserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;
    public final UserMapper userMapper;


    public void saveUser(UserDTO userDTO) {
        User userTemp = userMapper.DTOtoEntity(userDTO);
        userRepository.findUserByPhoneNumber(userDTO.getPhoneNumber()).ifPresent(user -> userTemp.setId(user.getId()));
        userRepository.save(userTemp);
        log.info("User with phone number {} was successfully saved", userTemp.getPhoneNumber());
    }

    public UserDTO getUser(String phoneNumber) {
        return userMapper.EntityToDTO(userRepository.findUserByPhoneNumber(phoneNumber).orElseGet(() -> userRepository.save(new User(phoneNumber))));
    }
}