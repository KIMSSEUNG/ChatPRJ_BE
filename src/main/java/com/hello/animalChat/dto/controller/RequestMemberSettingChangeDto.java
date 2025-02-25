package com.hello.animalChat.dto.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMemberSettingChangeDto {
    private Long changeId;
    private String mbti;
    private String animal;
    private char gender;
}
