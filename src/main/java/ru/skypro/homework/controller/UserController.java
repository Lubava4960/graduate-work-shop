package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.io.IOException;

/**
 * Контроллер для работы с пользователями
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")


@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    private final UserServiceImpl userService;

    @Operation(
            summary = "Обновление пароля",
            tags = "Пользователи")

    @PostMapping("/set_password")
    public void updateNewPassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        userService.changePassword(newPassword, authentication);
    }

    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            tags = "Пользователи"
    )
    @GetMapping("/me")
    public User getUser(Authentication authentication) {
        return userService.getUser(authentication);

    }

    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            tags = "Пользователи"
    )

    @PatchMapping("/me")
    public void updateUser(@RequestBody UpdateUser updateUser, Authentication authentication) {

        userService.updateUser(updateUser, authentication);

    }

    @Operation(
            summary = "Обновление аватара авторизованного пользователя",
            tags = "Пользователи"
    )

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public byte[] updateUserImage(@RequestParam("image") MultipartFile image, Authentication authentication) throws IOException {
        return userService.updateAvatar(image, authentication);
    }

    @GetMapping("/{id}/image")
    public byte[] getAvatar(@PathVariable int id) {
        return userService.getAvatar(id);
    }


}
