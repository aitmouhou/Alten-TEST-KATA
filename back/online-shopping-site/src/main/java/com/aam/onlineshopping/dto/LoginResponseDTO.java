package com.aam.onlineshopping.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private long expiresIn;
    public String getToken() {
        return token;
    }

}
