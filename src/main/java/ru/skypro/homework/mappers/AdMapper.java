package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.Ad;

@Mapper(componentModel = "spring")
public interface AdMapper {
    @Mapping(ignore = true,target="image")
    @Mapping(source="id",target="pk")
    @Mapping(source="user.firstName",target="authorFirstName")
    @Mapping(source="user.lastName",target="authorLastName")
    @Mapping(source="user.username",target="email")
    @Mapping(source="user.phone",target="phone")
    ExtendedAd toExtendedAd(Ad ad);

    @Mapping(source="id",target="pk")
    @Mapping(ignore = true,target="image")
    @Mapping(source="user.id",target="author")
    ru.skypro.homework.dto.Ad toAd(Ad ad);


}

