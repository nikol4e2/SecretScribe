package com.example.secretscribe.model.dto;

import com.example.secretscribe.model.Confession;
import lombok.Data;

@Data
public class commentDto {
    private String text;
    private int likes;
    private int dislikes;
    private Confession confession;

    public commentDto(String text, Confession confession) {
        this.text = text;
        this.likes = 0;
        this.dislikes = 0;
        this.confession = confession;
    }
}
