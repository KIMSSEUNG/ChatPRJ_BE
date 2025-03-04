package com.hello.animalChat.repository;

import com.hello.animalChat.domain.Member;
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

    public Message save(Message m){
        em.persist(m);
        return m;
    }

    public List<Message> findByReceiveMessage(Long id){
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
                " AND ";
        List<Message> findMessage = em.createQuery(jpql , Message.class)
            .setParameter("id", id)
            .getResultList();
        return findMessage;
    }
    
}
