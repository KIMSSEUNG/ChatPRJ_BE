package com.hello.animalChat.repository;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.hello.animalChat.Enum.LoginType;

import org.springframework.stereotype.Repository;
import com.hello.animalChat.domain.Member;
import com.hello.animalChat.dto.controller.RequestMemberSettingChangeDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
        Member find = em.find(Member.class,id);
        return find;
    }

    //이메일을 통한 정보 등록 확인
    public Optional<Member> findByEmail(String email , LoginType type){
        try{
            String jpql = "SELECT u FROM Member u WHERE u.email = :email AND u.loginType = :type";
            Member member = em.createQuery(jpql, Member.class)
                .setParameter("email", email) 
                .setParameter("type", type)
                .getSingleResult();
            return Optional.of(member);
        }catch(NoResultException e){
            log.warn(e.getMessage());
            return Optional.empty();
        }
    }

    public void updateMemberSetting(RequestMemberSettingChangeDto dto){
        Member findUser = em.find(Member.class , dto.getChangeId());
        if(findUser==null){
            throw new NoSuchElementException("Setting을 변경할 멤버가 없습니다.");
        }
        findUser.changeMemberSetting(dto);
    }

    public void updateMemberPW(Long memberId , String pw){
        Member findUser = em.find(Member.class , memberId);
        findUser.changeMemberPW(pw);
    }

    public void deleteMember(Long memberId){
        Member findUser = em.find(Member.class , memberId);
        if(findUser==null)return;
        em.remove(findUser);
    }

    public void clear(){
        em.flush();
        em.clear();
    }


}
