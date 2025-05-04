package com.hello.animalChat.domain.Fcm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class FCMToken {
    
    @Id
    @Column(name = "fcmtoken_id")
    private Long id;
    @Column(nullable = false , unique = true)
    private String token;
    
    public FCMToken(Long id , String token) {
        this.id =id;
        this.token = token;
    }
    
}
