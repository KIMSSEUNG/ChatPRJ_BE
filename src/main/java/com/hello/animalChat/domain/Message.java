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
<<<<<<< HEAD
    @JoinColumn(name = "member_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
=======
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
>>>>>>> ae50967bb6446f359b2a4d7dbcc6c4583e307d2d
    private Member receiver;

    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDateTime create_at;
    
    public Message(){}

<<<<<<< HEAD
    public Message(String message, LocalDateTime create_at) {
=======
    public Message(Member sender , Member receiver ,  String message, LocalDateTime create_at) {
        this.sender =sender;
        this.receiver = receiver;
>>>>>>> ae50967bb6446f359b2a4d7dbcc6c4583e307d2d
        this.message = message;
        this.create_at = create_at;
    }

}
