package com.hello.animalChat.dto.controller;

import com.hello.animalChat.Enum.LoginType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestMemberDto {
    private String email;
    private String password;
    private LoginType loginType;
    private String mbti;
    private String animal;
    private char gender;
    private LocalDateTime create_at;

}
