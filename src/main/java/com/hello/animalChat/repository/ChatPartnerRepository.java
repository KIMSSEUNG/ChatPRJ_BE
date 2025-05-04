package com.hello.animalChat.repository;

import com.hello.animalChat.domain.member.ChatPartner;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatPartnerRepository {

    private final EntityManager em;

    public Long save(ChatPartner c){
        em.persist(c);
        return c.getId();
    }

}
