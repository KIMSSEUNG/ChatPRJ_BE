package com.hello.animalChat.repository;

import org.springframework.stereotype.Repository;

import com.hello.animalChat.domain.Message;

import java.util.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MessageRepository {

    private final EntityManager em;

    public Message save(Message m){
        em.persist(m);
        return m;
    }

    public List<Message> findByReceiveMessage(Long id){
        String jpql = "SELECT m FROM Message m WHERE m.receiveId = :id";
        List<Message> findMessage = em.createQuery(jpql , Message.class)
            .setParameter("id", id)
            .getResultList();
        return findMessage;
    }
    
}
