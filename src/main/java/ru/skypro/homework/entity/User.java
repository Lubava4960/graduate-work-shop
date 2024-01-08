package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.Collection;

/**
 * Сущность пользователя
 */
@Builder
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    /**
     * Id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /**
     * Логин пользователя
     */
    @Column(name = "username")
    private String username;

    private String password;

    /**
     * имя пользователя
     */
    @Column(name="first_name")
    private String firstName;
    /**
     * фамилия пользователя
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * телефон пользователя
     */
    @Column(name="phone", length = 20)
    private String phone;
    /**
     * права доступа пользователя
     */
    @Column(name="role", nullable = false)
    @Enumerated( EnumType.STRING)
    private Role role;
    /**
     * aватар(фото) пользователя
     */

    @Column(name="image")
    private byte[] image;


}
