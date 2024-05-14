package com.example.secretscribe.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class adminDto {

    private String username;
    private String password;

    public adminDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
