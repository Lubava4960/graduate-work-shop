package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.service.impl.AdsServiceImpl;


/**
 * Контроллер для работы с объявлениями
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")

public class AdsController {
private final AdsServiceImpl adsService;



    @Operation(
            summary = "получение всех объявлений",
            tags= "объявления"
    )
    @GetMapping
    public Ads getAds(){

        return new Ads();
    }
    @Operation(
            summary = "добавление объявлений",
            tags= "объявления"
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public Ad addAds(@RequestPart("properties") CreateOrUpdateAd ads, @RequestPart("image") MultipartFile image) {
        return new Ad();

    }
    @Operation(
            summary = "получение информации об объявлении",
            tags= "объявления"
    )
    @GetMapping("/{id}")
    public Ad getAds(@PathVariable("id") int id){

        return new Ad();
    }
    @Operation(
            summary = "удаление объявлении",
            tags= "объявления"
    )
    @DeleteMapping("/{id}")
    public void deleteByIdDto(@PathVariable("id") int id){

    }
    @Operation(
            summary = "обновление информации об объявлении",
            tags= "объявления"
    )
    @PatchMapping("/{id}")
    public CreateOrUpdateAd updateCreateAdsDto(@PathVariable int id, @RequestBody CreateOrUpdateAd ads) {
        return new CreateOrUpdateAd();
    }
    @Operation(
            summary = "Получение информации авторизованного пользователя",
            tags= "объявления"
    )

    @GetMapping("/me")

    public Ads getLoginUserAds(){

        return new Ads();
    }
    @Operation(
            summary= "Обновление картинки объявления (по id)",
            tags= "объявления"
    )

    @PatchMapping("/{id}/image")
    public Ad updateAdPicture(@PathVariable("id") int id, @RequestParam("image") MultipartFile image){
        return new Ad();
    }



}

