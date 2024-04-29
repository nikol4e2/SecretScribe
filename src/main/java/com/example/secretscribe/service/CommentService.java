package com.example.secretscribe.service;

import com.example.secretscribe.model.Comment;
import com.example.secretscribe.model.Confession;

import java.util.Optional;

public interface CommentService {

    Comment saveComment(String text, Confession confession);
    Optional<Comment> findById(Long id);
    void addLikeToComment(Long id);
    void addDislikeToComment(Long id);
    void deleteById(Long id);
}
