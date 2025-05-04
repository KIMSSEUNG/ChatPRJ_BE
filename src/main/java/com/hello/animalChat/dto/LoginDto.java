

package com.hello.animalChat.dto;

import com.hello.animalChat.Enum.LoginType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class LoginDto {
    private String email;
    private LoginType loginType;
    private String password;

}