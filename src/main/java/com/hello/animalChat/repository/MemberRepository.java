package com.hello.animalChat.repository;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.hello.animalChat.Enum.LoginType;

import jakarta.persistence.NoResultException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import com.hello.animalChat.domain.member.Member;
import com.hello.animalChat.dto.member.MemberSettingChangeDto;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {
    
    private final EntityManager em;
    

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id){
        return em.find(Member.class,id);
    }

    // 지연 로딩 프록시 반환 (getReferenceById와 동일한 역할)
    public Member getReferenceById(Long id) {
        return em.getReference(Member.class, id);
    }

    //이메일을 통한 정보 등록 확인
    public Member findByEmail(String email , LoginType type){

        String jpql = "SELECT u FROM Member u WHERE u.email = :email AND u.loginType = :type";
        Member member = em.createQuery(jpql, Member.class)
                .setParameter("email", email)
                .setParameter("type", type)
                .getSingleResult();
        return member;

    }

    public boolean updateMemberSetting(MemberSettingChangeDto dto){
        Member findUser = em.find(Member.class , dto.getChangeId());
        if(findUser!=null){
            findUser.changeMemberSetting(dto);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean updateMemberPW(Long memberId , String pw){
        Member findUser = em.find(Member.class , memberId);
        if(findUser!=null){
            findUser.changeMemberPW(pw);
            return true;
        }
        else{
            return false;
        }

    }

    public void deleteMember(Long memberId){
        Member findUser = em.find(Member.class , memberId);
        em.remove(findUser);
    }

    public void clear(){
        em.flush();
        em.clear();
    }


}
