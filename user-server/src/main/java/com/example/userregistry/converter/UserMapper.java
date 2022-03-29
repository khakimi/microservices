package com.example.userregistry.converter;


import com.example.userregistry.dto.UserDTO;
import com.example.userregistry.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User DTOtoEntity(UserDTO userDTO);

    UserDTO EntityToDTO(User user);

}
