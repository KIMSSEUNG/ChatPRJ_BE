package com.hello.animalChat.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;
    
    private Long senderId;

    private Long receiverId;

    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDateTime create_at;
    
    public Message(){}

    public Message(Long senderId, Long receiverId, String message, LocalDateTime create_at) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.create_at = create_at;
    }

}
