package com.example.secretscribe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
     @ManyToOne
     @JoinColumn(name = "confession_id")
     @JsonIgnoreProperties("comments")
     private Confession confession;

    public Comment(String text,Confession confession) {
        this.text = text;
        this.likes = 0;
        this.dislikes = 0;
        this.confession=confession;
    }

}
