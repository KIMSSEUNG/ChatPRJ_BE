package com.hello.animalChat.service;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.member.Member;
import com.hello.animalChat.dto.fcm.FCMDto;
import com.hello.animalChat.dto.member.MemberDto;
import com.hello.animalChat.dto.member.MemberSettingChangeDto;
import com.hello.animalChat.repository.FCMRepository;
import com.hello.animalChat.repository.MemberRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final FCMRepository fcmTokenRepository;

    @Transactional
    public Long saveMember(MemberDto dto){
        Long id = memberRepository.save(
                Member.builder()
                        .loginType(dto.getLoginType())
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                        .age(dto.getAge())
                        .name(dto.getName())
                        .mbti(dto.getMbti())
                        .animal(dto.getAnimal())
                        .gender(dto.getGender())
                        .create_at(LocalDateTime.now())
                        .build()
        );

        //토큰 저장
        fcmTokenRepository.save(new FCMDto(id , dto.getToken()));

        return id;
    }

    public Member findReferenceById(Long id) {
        return memberRepository.getReferenceById(id);
    }

    public Member findMemberById(Long id){
        Member m =memberRepository.findById(id);
        if(m!=null){
            return m;
        }
        else{
            throw new NoResultException();
        }

    }


    public Member findMemberByEmail(String email , LoginType loginType){
        Member m = memberRepository.findByEmail(email , loginType);
        if(m!=null){
            return m;
        }
        else{
            throw new NoResultException();
        }
    }

    @Transactional
    public boolean changeMemberSetting(MemberSettingChangeDto dto){
        return memberRepository.updateMemberSetting(dto);
    }

    @Transactional
    public boolean changeMemberPW(Long memberId , String pw){
        try{
            memberRepository.updateMemberPW(memberId , pw);
            return true;
        }catch(NoResultException e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public void deleteMember(Long memberId){
        memberRepository.deleteMember(memberId);
        fcmTokenRepository.deleteToken(memberId);
    }

    public void entityManagerClear(){
        memberRepository.clear();
    }

}
