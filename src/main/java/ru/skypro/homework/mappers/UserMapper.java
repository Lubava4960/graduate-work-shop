package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
   @Mapping(source = "username",target="email")
   @Mapping(ignore = true,target="image")
    User userToUserDto(ru.skypro.homework.entity.User user);



}