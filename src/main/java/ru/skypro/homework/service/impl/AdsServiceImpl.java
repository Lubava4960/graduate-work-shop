package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdsServiceImpl {
    private final AdsRepository adsRepository;
    private final AdMapper adMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public Ads getAllAds() {
        Ads ads = new Ads();
        List<Ad> adsList = adsRepository.findAll();
        ads.setCount(adsList.size());
        ads.setResults(
                adsList.stream()
                        .map(e -> adMapper.toAd(e))
                        .map(e->{
                            e.setImage("/ads/"+e.getPk()+"/image");
                            return e;
                        })
                        .collect(Collectors.toList())
        );
        return ads;
    }

    public void createAd(CreateOrUpdateAd ads, MultipartFile image, Authentication authentication) throws IOException {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Юзер не найден"));
        Ad ad = new Ad();
        ad.setUser(user);
        ad.setImage(image.getBytes());
        ad.setTitle(ads.getTitle());
        ad.setDescription(ads.getDescription());
        ad.setPrice(ads.getPrice());
        adsRepository.save(ad);
    }

    public ru.skypro.homework.dto.ExtendedAd getAds(int id) {
        return adsRepository.findById(id).map(adMapper::toExtendedAd).map(e->{
            e.setImage("/ads/"+e.getPk()+"/image");
            return e;
        }).orElseThrow();
    }

    public void delete(int id) {
        commentRepository.deleteAllByAd_Id(id);
        adsRepository.deleteById(id);


    }

    public void updateAd(int id, CreateOrUpdateAd ads) {
        Ad ad=adsRepository.findById(id).orElseThrow();
        ad.setTitle(ads.getTitle());
        ad.setDescription(ads.getDescription());
        ad.setPrice(ads.getPrice());
        adsRepository.save(ad);
    }

    public Ads getMyAds(Authentication authentication) {
    List<ru.skypro.homework.dto.Ad>adsDto= adsRepository.findAllByUser_Username(authentication.getName()).stream()
        .map(adMapper::toAd)
            .map(e->{
                e.setImage("/ads/"+e.getPk()+"/image");
                return e;
            })
        .collect(Collectors.toList());
    Ads ads = new Ads();
    ads.setCount(adsDto.size());
    ads.setResults(adsDto);
    return ads;
    }

    public void updateImage(int id, MultipartFile image) throws IOException {
        Ad ad=adsRepository.findById(id).orElseThrow();
        ad.setImage(image.getBytes());
        adsRepository.save(ad);
    }
    public boolean hasRight(int id, Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Юзер не найден"));
        Ad ad =adsRepository.findById(id).orElseThrow();
        return user.getRole()== Role.ADMIN || user.getUsername().equals(ad.getUser().getUsername());
    }

    public byte[] getImage(int id) {
        Ad ad=adsRepository.findById(id).orElseThrow();
        return ad.getImage();
    }
}
