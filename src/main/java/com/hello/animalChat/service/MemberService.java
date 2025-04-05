package com.hello.animalChat.service;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.Member;
import com.hello.animalChat.dto.controller.FcmTokenDto;
import com.hello.animalChat.dto.controller.MemberDto;
import com.hello.animalChat.dto.controller.RequestMemberSettingChangeDto;
import com.hello.animalChat.repository.FcmTokenRepository;
import com.hello.animalChat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.*;

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
        Member member = new Member(dto.getLoginType() , dto.getEmail() , dto.getPassword() ,
                dto.getName() , dto.getMbti() , dto.getAnimal() , dto.getGender() , dto.getCreate_at());

        //토큰 저장
        fcmTokenRepository.save(new FcmTokenDto(member.getId() , dto.getToken()));

        return memberRepository.save(member);
    }

    public Member findMemberById(Long id){
        return memberRepository.findById(id);
    }


    public Member findMemberByEmail(String email , LoginType loginType){
        return memberRepository.findByEmail(email , loginType).orElse(new Member());
    }

    @Transactional
    public boolean changeMemberSetting(RequestMemberSettingChangeDto dto){
        try{
            memberRepository.updateMemberSetting(dto);
            return true;
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
