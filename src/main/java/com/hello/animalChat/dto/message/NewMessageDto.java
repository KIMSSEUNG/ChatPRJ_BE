package com.hello.animalChat.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewMessageDto {
    private Long senderId;
    private int minAge;
    private int maxAge;
    private char gender;
    private String message;
}
