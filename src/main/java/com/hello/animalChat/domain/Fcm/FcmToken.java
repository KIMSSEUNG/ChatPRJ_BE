package com.hello.animalChat.domain.Fcm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class FcmToken {
    
    @Id
    @Column(name = "fcmtoken_id")
    private Long id;
    private String token;
    
    public FcmToken(Long id , String token) {
        this.id =id;
        this.token = token;
    }
    
}
