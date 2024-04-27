package com.example.secretscribe.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
