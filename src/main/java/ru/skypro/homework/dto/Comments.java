package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO списка комментариев.
 */
@Data

public class Comments {

    private int count; // общее количество комментариев
    private List<Comment> results; // все комментарии
}