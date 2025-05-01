package com.hello.animalChat.service;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.member.Member;
import com.hello.animalChat.dto.FcmTokenDto;
import com.hello.animalChat.dto.member.MemberDto;
import com.hello.animalChat.dto.member.MemberSettingChangeDto;
import com.hello.animalChat.repository.FcmTokenRepository;
import com.hello.animalChat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.*;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final FcmTokenRepository fcmTokenRepository;

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
                        .build()
        );

        //토큰 저장
        fcmTokenRepository.save(new FcmTokenDto(id , dto.getToken()));

        return id;
    }

    public Member findMemberById(Long id){
        return memberRepository.findById(id);
    }


    public Member findMemberByEmail(String email , LoginType loginType){
        try {
            return memberRepository.findByEmail(email , loginType).orElse(Member.builder().build());
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Transactional
    public boolean changeMemberSetting(MemberSettingChangeDto dto){
        try{
            memberRepository.updateMemberSetting(dto);
            return true;
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean changeMemberPW(Long memberId , String pw){
        try{
            memberRepository.updateMemberPW(memberId , pw);
            return true;
        }catch(NoSuchElementException e){
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
