package com.hello.animalChat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseLoginDto {
    private Long id;
    private String name;
    private String mbti;
    private String animal;
    private char gender;
}
