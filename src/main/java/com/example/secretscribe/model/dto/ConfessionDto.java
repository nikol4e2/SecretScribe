package com.example.secretscribe.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ConfessionDto {

    private String text;
    private LocalDate date;
    private boolean isApproved;
    private int likes;
    private int dislikes;
    private List<Long> comments;
}
