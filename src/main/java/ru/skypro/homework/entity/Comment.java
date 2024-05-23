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
    @Column(name="id")
    private int id;

    /**
     * Дата и время комментария
     */
    @Column(name="created_at")
    private long createdAt;

    /**
     * Текст комментария
     */
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name="ad_id")
    private Ad ad;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}