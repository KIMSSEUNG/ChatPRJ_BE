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
    
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDateTime create_at;
    
    public Message(){}

    public Message(Member sender, Member receiver, String message, LocalDateTime create_at) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.create_at = create_at;
    }

}
