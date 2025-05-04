package com.hello.animalChat.domain.member;
import java.time.LocalDateTime;

import com.hello.animalChat.domain.Message;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.*;
import com.hello.animalChat.Enum.*;
import com.hello.animalChat.dto.member.MemberSettingChangeDto;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long partnerId;
    private LocalDateTime created_at;

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

