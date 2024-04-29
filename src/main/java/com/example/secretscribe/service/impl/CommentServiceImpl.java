package com.example.secretscribe.service.impl;

import com.example.secretscribe.model.Comment;
import com.example.secretscribe.model.Confession;
import com.example.secretscribe.repository.CommentRepository;
import com.example.secretscribe.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl  implements CommentService {
    private final CommentRepository commentRepository;


    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment saveComment(String text, Confession confession) {
        Comment comment=new Comment(text,confession);
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void addLikeToComment(Long id) {
        Comment comment=(Comment) commentRepository.findById(id).get();
        if(comment!=null)
        {
            comment.setLikes(comment.getLikes()+1);
            commentRepository.save(comment);
        }

    }

    @Override
    public void addDislikeToComment(Long id) {
        Comment comment=(Comment) commentRepository.findById(id).get();
        if(comment!=null)
        {
            comment.setDislikes(comment.getDislikes()+1);
            commentRepository.save(comment);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.commentRepository.deleteById(id);
    }
}
