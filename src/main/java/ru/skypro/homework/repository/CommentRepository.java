package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    void deleteAllByAd_Id(int id);
    List<Comment>findAllByAd_Id(int id);

}
