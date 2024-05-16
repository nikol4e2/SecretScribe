package com.example.secretscribe.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class AdminDto {

    private String username;
    private String password;

    public AdminDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
