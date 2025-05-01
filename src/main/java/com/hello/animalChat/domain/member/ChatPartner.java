package com.hello.animalChat.domain.member;
import java.time.LocalDateTime;

import com.hello.animalChat.domain.Message;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.*;
import com.hello.animalChat.Enum.*;
import com.hello.animalChat.dto.member.MemberSettingChangeDto;
import org.springframework.cglib.core.Local;;


@Entity
@Getter
public class ChatPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long partnerId;
    private LocalDateTime created_at;

    public ChatPartner(){}

    public ChatPartner(Long id, Long userId, Long partnerId, LocalDateTime created_at) {
        this.id = id;
        this.userId = userId;
        this.partnerId = partnerId;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "ChatPartner{" +
                "id=" + id +
                ", userId=" + userId +
                ", partnerId=" + partnerId +
                ", created_at=" + created_at +
                '}';
    }
}

