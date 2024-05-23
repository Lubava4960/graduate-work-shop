package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *DTO юзера
 */
@Data

public class User {
    private int id; // id пользователя
    private String email; // логин пользователя
    private String firstName; // имя пользователя
    private String lastName; // фамилия пользователя
    private String phone; // телефон пользователя
    private Role role;//права админа или пользователя
    private String image; // ссылка на аватар пользователя


}
