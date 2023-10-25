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

    private Integer id;

    /**
     * Цена объявления
     */
    @Column(name="price")
    private Integer price;
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
    private String imagePath;


    @ManyToOne
    private User user;

     @OneToMany(mappedBy = "ads", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "ad" ,cascade = CascadeType.ALL, orphanRemoval = true)

     private Collection<Comment> comment;

}
