package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.impl.AdsServiceImpl;

import java.io.IOException;


/**
 * Контроллер для работы с объявлениями
 *
 */
@Slf4j
@RestController
//@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")

public class AdsController {
private final AdsServiceImpl adsService;

    public AdsController(AdsServiceImpl adsService) {
        this.adsService = adsService;
    }


    @Operation(
            summary = "получение всех объявлений",
            tags= "объявления"
    )
    @GetMapping
    public Ads getAds(){

        return adsService.getAllAds();
    }
    @Operation(
            summary = "добавление объявлений",
            tags= "объявления"
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public void addAds(@RequestPart("properties") CreateOrUpdateAd ads, @RequestPart("image") MultipartFile image, Authentication authentication) throws IOException {
       adsService.createAd(ads,image, authentication);

    }
    @Operation(
            summary = "получение информации об объявлении",
            tags= "объявления"
    )
    @GetMapping("/{id}")
    public ExtendedAd getAds(@PathVariable("id") int id){

        return adsService.getAds(id);
    }
    @Operation(
            summary = "удаление объявлении",
            tags= "объявления"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("@adsServiceImpl.hasRight(#id,authentication)")
    public void deleteByIdDto(@PathVariable("id") int id){
     adsService.delete(id);
    }
    @Operation(
            summary = "обновление информации об объявлении",
            tags= "объявления"
    )
    @PatchMapping("/{id}")
    @PreAuthorize("@adsServiceImpl.hasRight(#id,authentication)")
    public void updateCreateAdsDto(@PathVariable int id, @RequestBody CreateOrUpdateAd ads) {
        adsService.updateAd(id,ads);
    }
    @Operation(
            summary = "Получение информации авторизованного пользователя",
            tags= "объявления"
    )

    @GetMapping("/me")

    public Ads getLoginUserAds(Authentication authentication){
    return adsService.getMyAds(authentication);
    }
    @Operation(
            summary= "Обновление картинки объявления (по id)",
            tags= "объявления"
    )

    @PatchMapping("/{id}/image")
    public void updateAdPicture(@PathVariable("id") int id, @RequestParam("image") MultipartFile image) throws IOException {
       adsService.updateImage(id, image);
    }
    @Operation(
            summary= "Получение картинки объявления (по id)",
            tags= "объявления"
    )


    @GetMapping("/{id}/image")
    public byte[] getImage(@PathVariable int id) {
        return adsService.getImage(id);
    }

}

