package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "id",target="pk")
    @Mapping(source = "user.id",target="author")
    @Mapping(source = "user.firstName",target="authorFirstName")
    Comment toDto(ru.skypro.homework.entity.Comment comment);



}
