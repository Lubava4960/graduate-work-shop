package ru.skypro.homework.dto;

import lombok.Data;

/**
 * DTO Полного формата объвления
 */

@Data
public class ExtendedAd {

    private int pk; // id объявления
    private String authorFirstName;
    private String authorLastName; //
    private String description;//описание
    private String email;
    private String image; // ссылка на картинку объявления
    private String phone; //телефон
    private int price; // цена объявления
    private String title; // заголовок объявления

}
