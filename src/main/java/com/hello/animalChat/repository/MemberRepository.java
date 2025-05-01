package com.hello.animalChat.repository;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.hello.animalChat.Enum.LoginType;

import org.springframework.stereotype.Repository;
import com.hello.animalChat.domain.member.Member;
import com.hello.animalChat.dto.member.MemberSettingChangeDto;

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
        member.setCreateAt(LocalDateTime.now());
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
            throw new NoResultException("해당하는 이메일이 없습니다.");
        }
    }

    public void updateMemberSetting(MemberSettingChangeDto dto){
        Member findUser = em.find(Member.class , dto.getChangeId());
        if(findUser==null){
            throw new NoSuchElementException("Setting을 변경할 멤버가 없습니다.");
        }
        findUser.changeMemberSetting(dto);
    }

    public void updateMemberPW(Long memberId , String pw){
        Member findUser = em.find(Member.class , memberId);
        if(findUser==null){
            throw new NoSuchElementException("Password를 변경할 멤버가 없습니다.");
        }
        findUser.changeMemberPW(pw);
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
