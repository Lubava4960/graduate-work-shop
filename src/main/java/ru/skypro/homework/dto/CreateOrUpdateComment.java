package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO создания комментария.
 */
@Data

public class CreateOrUpdateComment {

    private String text; // текст комментария
}
