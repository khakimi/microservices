package com.example.userregistry.convertor;

import com.example.userregistry.models.User;
import com.example.userregistry.models.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

    @Autowired
    public ModelMapper modelMapper;

    public User DTOtoEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO entityToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }


}
