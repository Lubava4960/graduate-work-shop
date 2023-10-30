package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Контроллер для работы с пользователями
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")


@CrossOrigin(value = "http://localhost:3000")
public class UserController{


    @Operation (
            summary = "Обновление пароля",
            tags= "Пользователи")

    @PostMapping("/set_password")
    public NewPassword updateNewPassword(@RequestBody NewPassword newPassword){
        return new NewPassword();
    }

    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            tags="Пользователи"
    )
    @GetMapping("/me")
    public User getUser(){
        return new User();

    }
    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            tags =  "Пользователи"
    )

    @PatchMapping("/me")
    public User updateUser(@RequestBody User user) {

        System.out.println("hello");
        return new User();

    }
    @Operation(
            summary= "Обновление аватара авторизованного пользователя",
            tags =  "Пользователи"
    )

    @PatchMapping (value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public byte[] updateUserImage(@RequestParam("image") MultipartFile image){
        return null;
    }




}
