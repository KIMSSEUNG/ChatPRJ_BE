package com.hello.animalChat.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMessageDto {
    private Long senderId;
    private int minAge;
    private int maxAge;
    private char gender;
    private String message;
}
