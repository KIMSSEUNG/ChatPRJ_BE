package com.hello.animalChat.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSettingChangeDto {
    private Long changeId;
    private String mbti;
    private String animal;
    private char gender;
}
