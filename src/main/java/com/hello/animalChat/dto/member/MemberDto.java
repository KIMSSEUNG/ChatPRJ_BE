package com.hello.animalChat.dto.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hello.animalChat.Enum.LoginType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    @JsonProperty("type")
    private LoginType loginType;
    private String email;
    private String password;
    private String name;
    private String age;
    private String mbti;
    private String animal;
    private String token;
    private char gender;
    private LocalDateTime create_at;

}
