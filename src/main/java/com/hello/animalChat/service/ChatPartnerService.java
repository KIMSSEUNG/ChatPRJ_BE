package com.hello.animalChat.service;

import com.hello.animalChat.domain.member.ChatPartner;
import com.hello.animalChat.repository.ChatPartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatPartnerService {

    private final ChatPartnerRepository chatPartnerRepository;

    @Transactional
    public Long save(Long senderId , Long receiveId){
        ChatPartner chatPartner = ChatPartner.builder()
                .userId(senderId)
                .partnerId(receiveId)
                .created_at(LocalDateTime.now())
                .build();

        return chatPartnerRepository.save(chatPartner);
    }

}
