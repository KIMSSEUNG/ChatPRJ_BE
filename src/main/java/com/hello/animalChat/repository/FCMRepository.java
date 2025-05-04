package com.hello.animalChat.repository;

import com.hello.animalChat.dto.fcm.FCMDto;
import org.springframework.stereotype.Repository;

import com.hello.animalChat.domain.Fcm.FCMToken;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FCMRepository {

    private final EntityManager em;
    
    public void save(FCMDto dto) {
        FCMToken entity = new FCMToken(dto.getUserId(), dto.getToken());
        em.persist(entity);
    }

    public String findByUserToken(Long id) {
        String jpql = """
                Select f.token From FCMToken f 
                where f.id = :id
                """;

        String token = em.createQuery(jpql,String.class)
            .setParameter("id", id)
            .getSingleResult();

        return token;
    }

    //토큰 삭제
    public void deleteToken(Long id) {
        String jpql = """
            delete From FCMToken f 
            where f.id = :id
            """;
    
        em.createQuery(jpql)
            .setParameter("id", id)
                .executeUpdate();
    }

}
