package com.hello.animalChat.service;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.Member;
import com.hello.animalChat.dto.controller.RequestMemberDto;
import com.hello.animalChat.dto.controller.RequestMemberSettingChangeDto;
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

    @Transactional
    public Long saveMember(RequestMemberDto dto){
        Member member = new Member(dto.getLoginType() , dto.getEmail() , dto.getPassword() ,
                dto.getName() , dto.getMbti() , dto.getAnimal() , dto.getGender() , dto.getCreate_at());
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
    public boolean deleteMember(Long memberId){
        try{
            memberRepository.deleteMember(memberId);
            return true;
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void entityManagerClear(){
        memberRepository.clear();
    }

}
