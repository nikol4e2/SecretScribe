package com.example.secretscribe.service;

import com.example.secretscribe.model.Comment;

import java.util.Optional;

public interface CommentService {

    Comment saveComment(String text);
    Optional<Comment> findById(Long id);
    void addLikeToComment(Long id);
    void addDislikeToComment(Long id);
    void deleteById(Long id);
}
