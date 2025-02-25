package com.hello.animalChat.dto.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMemberPWChangeDto {
    private Long id;
    private String changePW;
}
