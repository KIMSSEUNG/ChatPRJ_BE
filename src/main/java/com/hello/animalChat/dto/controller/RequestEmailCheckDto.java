package com.hello.animalChat.dto.controller;

import com.hello.animalChat.Enum.LoginType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEmailCheckDto {
    private String email;
    private LoginType loginType;
}
