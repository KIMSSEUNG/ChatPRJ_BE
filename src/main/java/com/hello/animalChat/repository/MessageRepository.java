package com.hello.animalChat.repository;

import com.hello.animalChat.dto.message.MessageDto;
import com.hello.animalChat.dto.message.NewMessageDto;
import org.springframework.stereotype.Repository;

import com.hello.animalChat.domain.Message;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MessageRepository {

    private final EntityManager em;

    public Long save(Message m){
        em.persist(m);
        return m.getId();
    }

    public List<Message> findByReceiveAllMessage(Long id){
        String jpql = "SELECT m FROM Message m" +
                " join fetch m.sender"+
                " join fetch m.receiver"+
                " WHERE m.receiver.id = :id";
        List<Message> findMessage = em.createQuery(jpql , Message.class)
            .setParameter("id", id)
            .getResultList();
        return findMessage;
    }

    public List<Message> findByNewMessage(Long id , LocalDateTime lastReceiveTime){
        String jpql = "SELECT m FROM Message m" +
                " join fetch m.sender"+
                " join fetch m.receiver"+
                " WHERE m.receiver.id = :id"+
                " AND m.create_at = :time";
        List<Message> findMessage = em.createQuery(jpql , Message.class)
            .setParameter("id", id)
            .setParameter("time", lastReceiveTime)
            .getResultList();
        return findMessage;
    }

    public Long newRandomId(NewMessageDto dto) {

        String jpql = """
                SELECT m.id FROM Member m 
                WHERE NOT EXISTS (
                    SELECT c.partnerId FROM ChatPartner c 
                    WHERE c.userId = :id AND c.partnerId = m.id 
                )
                AND m.gender = :gender 
                AND m.id != :id 
                AND m.age BETWEEN :low AND :high
                """;
        List<Long> partner= em.createQuery(jpql , Long.class)
                .setParameter("id",dto.getSenderId())
                .setParameter("gender",dto.getGender())
                .setParameter("low",dto.getMinAge())
                .setParameter("high",dto.getMaxAge())
                .getResultList();

        if(partner.size()==0)
            return 0L;

        //랜덤 뽑기 생성
        Random rand = new Random();
        Long randomId = partner.get(rand.nextInt(partner.size()));

        return randomId;
    }

    public Message findById(Long id) {
        return em.find(Message.class,id);
    }
}
