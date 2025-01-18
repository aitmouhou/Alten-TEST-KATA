package com.aam.onlineshopping.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String username;
    private String firstname;
    private String email;
    private String password;
}
