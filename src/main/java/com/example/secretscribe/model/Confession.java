package com.example.secretscribe.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "confession")
public class Confession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private LocalDate date;
    private boolean isApproved;
    private int likes;
    private int dislikes;
    @OneToMany
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
