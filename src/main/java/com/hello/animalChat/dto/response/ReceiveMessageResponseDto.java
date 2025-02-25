package com.hello.animalChat.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveMessageResponseDto {
    private Long send_id;
    private String mbti;
    private String animal;
    private char gender;
    private List<String> message;
}