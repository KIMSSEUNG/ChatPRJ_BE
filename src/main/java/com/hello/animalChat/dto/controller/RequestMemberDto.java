package com.hello.animalChat.dto.controller;

import com.hello.animalChat.Enum.LoginType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMemberDto {
    private String email;
    private String password;
    private LoginType loginType;
    private String name;
    private String mbti;
    private String animal;
    private char gender;
    private LocalDateTime create_at;

}
