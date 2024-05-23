package ru.skypro.homework.dto;

import lombok.Data;

/**
 * DTO обновления Юзера
 */

@Data
public class UpdateUser {
    private String firstName;
    private String lastName;
    private String phone;
}
