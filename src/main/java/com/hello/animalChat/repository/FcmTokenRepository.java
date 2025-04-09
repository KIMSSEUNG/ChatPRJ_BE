package com.hello.animalChat.repository;

import org.springframework.stereotype.Repository;

import com.hello.animalChat.domain.Fcm.FcmToken;
import com.hello.animalChat.dto.controller.FcmTokenDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FcmTokenRepository {

    private final EntityManager em;
    
    public void save(FcmTokenDto dto) {
        
        try{
            findByUserToken(dto.getUserId());
        }
        catch(NoResultException e){
            //해당하는 토큰이 없음으로 등록
            System.out.println(dto.getUserId());
            FcmToken entity = new FcmToken(dto.getUserId(), dto.getToken());
            em.persist(entity);
        }
    }

    public String findByUserToken(Long id) {
        String jpql = """
                Select f.token From FcmToken f 
                where f.id = :id
                """;
        
        String token = em.createQuery(jpql,String.class)
            .setParameter("id", id)
            .getSingleResult();

        if(token==null){
            throw new NoResultException("해당하는 토큰이 없습니다.");
        }

        return token;
    }

    //토큰 삭제
    public void deleteToken(Long id) {
        String jpql = """
            delete From FcmToken f 
            where f.id = :id
            """;
    
        em.createQuery(jpql)
            .setParameter("id", id);
    }

}
