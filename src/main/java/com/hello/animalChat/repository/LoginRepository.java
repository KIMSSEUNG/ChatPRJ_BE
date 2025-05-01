package com.hello.animalChat.repository;

import org.springframework.stereotype.Repository;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.member.Member;
import com.hello.animalChat.dto.LoginDto;
import com.hello.animalChat.dto.response.ResponseLoginDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoginRepository {
    
    private final EntityManager em;
    private final MemberRepository memberRepository;

    
    public void passwordCheck(LoginType loginType , String email , String password){
        try{
            if(loginType==LoginType.BASIC){
                String jpql = "SELECT m.password FROM Member m WHERE m.email = :email"
                +" AND m.password = :password";
                em.createQuery(jpql,String.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
            }
        }catch(NoResultException e){
            throw new NoResultException("비밀번호가 틀렸습니다.");
        }
    }
    
    public ResponseLoginDto loginBasic(LoginDto dto){
        Member m = memberRepository.findByEmail(dto.getEmail(), dto.getLoginType()).orElse(null);
        //비밀 번호 확인
        passwordCheck(dto.getLoginType() , dto.getEmail() , dto.getPassword());

        return new ResponseLoginDto(m.getId(), m.getName()
                    , m.getMbti(), m.getAnimal(), m.getGender());
        
    }

    public ResponseLoginDto loginGoogle(LoginDto dto){
        Member m = memberRepository.findByEmail(dto.getEmail(), dto.getLoginType()).orElse(null);
        //구글은 아이디로만 확인

        return new ResponseLoginDto(m.getId(), m.getName()
                , m.getMbti(), m.getAnimal(), m.getGender());

    }


}
