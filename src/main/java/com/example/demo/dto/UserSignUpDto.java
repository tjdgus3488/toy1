package com.example.demo.dto;

import lombok.Data;

@Data
public class UserSignUpDto {
    private String email;
    private String password;
    private String nickname;
}