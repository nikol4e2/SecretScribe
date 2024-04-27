package com.example.secretscribe.repository;

import com.example.secretscribe.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {



}
