package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Сущность комментария
 */
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="comments")
public class Comment {
    /**
     * id  комментария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Integer pk;

    /**
     * Дата и время комментария
     */
    @Column(name="create_ad")
    private Long createdAt;

    /**
     * Текст комментария
     */
    @Column(name = "text")
    private String test;

    @ManyToOne
    private Ad ads;


}