package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.repository.CommentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class CommentServiceImpl {
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

   // public List<Comment> CommentServiceImpl(CommentRepository commentRepository) {
    //    this.commentRepository = commentRepository;


      //  public List<Comment> getAllComments(Comment comment) {
       //     return commentRepository.findAll();
     //   }

    //    public Comment getCommentById(Integer id) {
    //        return commentRepository.findById(id)
     //               .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
     //   }

     //   public Comment createComment(Comment ) {
      //      return commentRepository.save(Comment comment);
      //  }

       // public void deleteCommentById(Integer id) {
       //     commentRepository.deleteById(id);
      //  }

   // }
}
