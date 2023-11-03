package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;


    public void create(int id, CreateOrUpdateComment comments, Authentication authentication) {
       Comment comment=new Comment() ;
       User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Юзер не найден"));
        Ad ad=adsRepository.findById(id).orElseThrow();
        comment.setUser(user);
        comment.setAd(ad);
        comment.setText(comments.getText());
        comment.setCreatedAt(System.currentTimeMillis());
        commentRepository.save(comment);
    }


    public Comments getComments(int id) {
    Comments comments=new Comments();
    List<ru.skypro.homework.dto.Comment>commentList=commentRepository.findAllByAd_Id(id).stream()
            .map(commentMapper::toDto)
            .map(e->{
                e.setAuthorImage("/users/"+e.getAuthor()+"/image");
                return e;
            })
            .collect(Collectors.toList());
    comments.setCount(commentList.size());
    comments.setResults(commentList);
    return comments;

    }

    public void delete(Integer id, Integer commentId) {
    commentRepository.deleteById(commentId);

    }

    public void update(Integer id, Integer commentId, CreateOrUpdateComment createOrUpdateComment) {
    Comment comment=commentRepository.findById(commentId).orElseThrow();
    comment.setText(createOrUpdateComment.getText());
    commentRepository.save(comment);
    }
    public boolean hasRight(int id, Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Юзер не найден"));
        Comment comment=commentRepository.findById(id).orElseThrow();
        return user.getRole()== Role.ADMIN || user.getUsername().equals(comment.getUser().getUsername());
    }



}
