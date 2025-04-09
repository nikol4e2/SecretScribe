package com.example.secretscribe.service.impl;

import com.example.secretscribe.model.Comment;
import com.example.secretscribe.model.Confession;
import com.example.secretscribe.model.dto.CommentDto;
import com.example.secretscribe.model.exceptions.CommentNotFoundException;
import com.example.secretscribe.model.exceptions.ConfessionNotFoundException;
import com.example.secretscribe.repository.CommentRepository;
import com.example.secretscribe.repository.ConfessionRepository;
import com.example.secretscribe.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl  implements CommentService {
    private final CommentRepository commentRepository;

    private final ConfessionRepository confessionRepository;


    public CommentServiceImpl(CommentRepository commentRepository, ConfessionRepository confessionRepository) {
        this.commentRepository = commentRepository;
        this.confessionRepository = confessionRepository;
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
        Comment comment= this.commentRepository.findById(id).orElseThrow(()->new CommentNotFoundException(id));


        comment.setLikes(comment.getLikes()+1);
        commentRepository.save(comment);


    }

    @Override
    public void addDislikeToComment(Long id) {
       Comment comment= this.commentRepository.findById(id).orElseThrow(()->new CommentNotFoundException(id));
       comment.setDislikes(comment.getDislikes()+1);
       commentRepository.save(comment);


    }

    @Override
    public void deleteById(Long id) {
        this.commentRepository.deleteById(id);
    }

    @Override
    public Comment saveComment(CommentDto commentDto) {
        Confession confession=confessionRepository.findById(commentDto.getConfession()).orElseThrow(()->new ConfessionNotFoundException(commentDto.getConfession()));
        Comment comment=new Comment(commentDto.getText(),confession);
        return commentRepository.save(comment);

    }
}
