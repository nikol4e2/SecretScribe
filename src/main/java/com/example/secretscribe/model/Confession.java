package com.example.secretscribe.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Confession {

    private Long id;
    private String text;
    private LocalDate date;
    private boolean isApproved;
    private int likes;
    private int dislikes;
    List<Comment> comments;


    public Confession(String text, LocalDate date) {
        this.text = text;
        this.date = date;
        this.isApproved = false;
        this.likes = 0;
        this.dislikes = 0;
        this.comments = new ArrayList<>();
    }
}
