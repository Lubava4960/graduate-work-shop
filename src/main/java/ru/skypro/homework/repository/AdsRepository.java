package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Ad;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ad, Integer>
{
    List<Ad> findAllByUser_Username(String username);

}
