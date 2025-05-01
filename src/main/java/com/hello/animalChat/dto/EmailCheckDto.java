package com.hello.animalChat.dto;

import com.hello.animalChat.Enum.LoginType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailCheckDto {
    private String email;
    private LoginType loginType;
}
