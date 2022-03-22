package com.example.userregistry.converter;

import com.example.userregistry.dto.UserDTO;
import com.example.userregistry.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    User DTOtoEntity(UserDTO userDTO);
    UserDTO EntitytoDTO(User user);
}
