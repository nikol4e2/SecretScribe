package com.example.secretscribe.service;

import com.example.secretscribe.model.Comment;
import com.example.secretscribe.model.Confession;

import java.util.List;
import java.util.Optional;

public interface ConfessionService {

    Optional<Confession> save(String text);

    List<Confession> findAll();
    List<Confession> findByText(String text);
    Optional<Confession> findById(Long id);
    Confession approveConfession(Long id);
    Confession addCommentToConfession(Long id, Comment comment);
    void addLikeToConfession(Long id);
    void addDislikeToConfession(Long id);

    void deleteById(Long id);
}
