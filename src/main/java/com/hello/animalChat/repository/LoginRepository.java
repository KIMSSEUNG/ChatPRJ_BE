package com.hello.animalChat.repository;

import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.member.Member;
import com.hello.animalChat.dto.LoginDto;
import com.hello.animalChat.dto.response.ResponseLoginDto;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoginRepository {
    
    private final EntityManager em;
    
    public ResponseLoginDto login(LoginDto dto){
        //비밀 번호 확인
        Member m =Check(dto);

        return new ResponseLoginDto(m.getId(), m.getName()
                    , m.getMbti(), m.getAnimal(), m.getGender());
    }


    public Member Check(LoginDto dto){

        String jpql = "SELECT m FROM Member m WHERE m.loginType = :type"
                +" AND m.email = :email";
        if(!dto.getLoginType().equals(LoginType.GOOGLE)){
            jpql+=" AND m.password = :password";
        }

        TypedQuery<Member> query = em.createQuery(jpql, Member.class)
                .setParameter("type", dto.getLoginType())
                .setParameter("email", dto.getEmail());
        if(!dto.getLoginType().equals(LoginType.GOOGLE)){
            query.setParameter("password", dto.getPassword());
        }

        return query.getSingleResult();
    }


}
