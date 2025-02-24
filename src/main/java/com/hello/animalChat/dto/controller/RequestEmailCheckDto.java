package com.hello.animalChat.dto.controller;

import com.hello.animalChat.Enum.LoginType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestEmailCheckDto {
    private String email;
    private LoginType loginType;
}
