package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data

public class Ad {
    private int author; // id автора объявления
    private String image; // ссылка на картинку объявления
    private int pk; // id объявления
    private int price; // цена объявления
    private String title; // заголовок объявления


}

