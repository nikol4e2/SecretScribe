package com.example.secretscribe.repository;

import com.example.secretscribe.model.Comment;
import com.example.secretscribe.model.Confession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfessionRepository extends JpaRepository<Confession,Long> {

    Optional<Confession> findByTextContains(String text);
    List<Optional> findAllByApproved(boolean approved);


}
