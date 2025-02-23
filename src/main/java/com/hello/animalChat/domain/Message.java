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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member receiver;

    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDateTime create_at;
    
    public Message(){}

    public Message(String message, LocalDateTime create_at) {
        this.message = message;
        this.create_at = create_at;
    }

}
