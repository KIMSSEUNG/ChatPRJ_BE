package com.hello.animalChat.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberPWChangeDto {
    private Long id;
    private String changePW;
}
