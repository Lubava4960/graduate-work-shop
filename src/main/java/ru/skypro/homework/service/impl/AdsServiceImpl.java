package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.AdsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdsServiceImpl  {
 private final AdsRepository adsRepository;
 private final AdMapper adMapper;
 public Ads getAllAds(){
     Ads ads = new Ads();
     List<Ad>adsList=adsRepository.findAll();
     ads.setCount(adsList.size());
     ads.setResults(
             adsList.stream()
                     .map(e->adMapper.toAd(e))
                     .collect(Collectors.toList())
     );
     return ads;
    }

}
