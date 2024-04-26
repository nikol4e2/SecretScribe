package com.example.secretscribe.model;

import lombok.Data;

@Data
public class Comment {

     private Long id;
     private String text;
     private int likes;
     private int dislikes;

    public Comment(String text) {
        this.text = text;
        this.likes = 0;
        this.dislikes = 0;
    }
}
