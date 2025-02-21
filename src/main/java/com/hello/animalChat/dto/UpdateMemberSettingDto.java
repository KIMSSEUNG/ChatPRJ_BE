package com.hello.animalChat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateMemberSettingDto {
    private String mbti;
    private String animal;
    private char gender;
}
