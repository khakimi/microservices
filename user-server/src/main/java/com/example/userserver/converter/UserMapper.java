package com.example.userserver.converter;

import com.example.userserver.dto.UserDTO;
import com.example.userserver.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User DTOtoEntity(UserDTO userDTO);

    UserDTO EntityToDTO(User user);

}