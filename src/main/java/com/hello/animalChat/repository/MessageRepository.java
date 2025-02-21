package com.hello.animalChat.repository;

import org.aspectj.bridge.Message;
import org.springframework.stereotype.Repository;
import java.util.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MessageRepository {

    private final EntityManager em;

    public List<Message> findByReceiveMessage(Long id){
        String jpql = "SELECT m FROM Message m WHERE m.receiver = :id";
        List<Message> findMessage = em.createQuery(jpql , Message.class).getResultList();
        return findMessage;
    }
    
}
