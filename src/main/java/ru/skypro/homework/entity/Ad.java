package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

/**
 * Сущность объявления.
 */
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ads")

public class Ad {
    /**
     * Id объявления
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")

    private int id;

    /**
     * Цена объявления
     */
    @Column(name="price")
    private int price;
    /**
     * Заголовок объявления
     */
    @Column(name="title")
    private String title;
    /**
     * Описание объявления
     */
    @Column(name="description")
    private String description;

    /**
     * Картинка объявления
     */
    @Column(name="image_path")
    private byte[] image;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;



}
