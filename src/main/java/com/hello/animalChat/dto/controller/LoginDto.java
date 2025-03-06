

package com.hello.animalChat.dto.controller;

import com.hello.animalChat.Enum.LoginType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {

    private String email;
    private LoginType loginType;
    private String password;

}