package com.hello.animalChat.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSettingChangeDto {
    private Long changeId;
    private String mbti;
    private String animal;
}
