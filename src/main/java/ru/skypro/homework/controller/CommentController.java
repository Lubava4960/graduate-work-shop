package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.service.impl.CommentServiceImpl;


/**
 * Контроллер для работы с комментариями
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")


public class CommentController {

    private  final CommentServiceImpl commentService;


    @GetMapping("/{id}/comments")
    @Operation(
            summary = "Получить комментарии объявления",
            description = "Нужно написать id автора ",
            tags= "Комментрии"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Комментарий найден"
    )
    @ApiResponse(
            responseCode = "401",
            description = "для того чтобы найти комментарий необходимо авторизоваться"
    )
    @ApiResponse(
            responseCode ="404",
            description = "страница не найдена"
    )

    public Comments getComment(@PathVariable(value = "id") int id){
        return commentService.getComments(id);
    }

    @PostMapping("/{id}/comments")
    @Operation(
            summary = "Добавить комментарий к объявлению",
            description = "Нужно написать комментарий ",
            tags= "Комментрии"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Комментарий добавлен"
    )
    @ApiResponse(
            responseCode = "401",
            description = "для того чтобы оставить комментарий необходимо авторизоваться"
    )
    @ApiResponse(
            responseCode ="404",
            description = "страница не найдена"
    )


    public void addComment(@PathVariable("id") int id,
                           @RequestBody CreateOrUpdateComment comments, Authentication authentication){

        commentService.create(id,comments, authentication);
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    @Operation(
            summary = "Удаление комментарий",
            description = "нужно написать комментарий id и рекламу id",
            tags= "Комментрии"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось удалить комментарий"
    )
    @ApiResponse(
            responseCode = "401",
            description = "чтобы удалить комментарий необходимо авторизоваться"
    )
    @ApiResponse(
            responseCode = "403",
            description = "отсутствуют права доступа"
    )
    @ApiResponse(
            responseCode ="404",
            description = "страница не найдена"
    )
    @PreAuthorize("@commentServiceImpl.hasRight(#commentId,authentication)")
    public void deleteCommend(@PathVariable Integer id,
                                 @PathVariable Integer commentId){
        commentService.delete(id, commentId);
    }


    @PatchMapping("/{id}/comments/{commentId}")
    @Operation(
            summary = "обновление комментария",
            description = "нужно написать комментарий id ",
            tags= "Комментрии"
    )
    @ApiResponse(
            responseCode = "200",
            description = "комментарий обновлён"
    )
    @ApiResponse(
            responseCode = "401",
            description = "чтобы обновить комментарий необходимо авторизоваться"
    )
    @ApiResponse(
            responseCode = "403",
            description = "отсутствуют права доступа"
    )
    @ApiResponse(
            responseCode ="404",
            description = "страница не найдена"
    )
    @PreAuthorize("@commentServiceImpl.hasRight(#commentId,authentication)")
    public void  updateComment(@PathVariable Integer id,
                                                 @PathVariable Integer commentId,
                                                 @RequestBody CreateOrUpdateComment comment) {
       commentService.update(id,commentId, comment);
    }






}
