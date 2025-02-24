package com.hello.animalChat.service;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.Member;
import com.hello.animalChat.dto.UpdateMemberSettingDto;
import com.hello.animalChat.dto.controller.RequestMemberDto;
import com.hello.animalChat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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
                dto.getMbti() , dto.getAnimal() , dto.getGender() , dto.getCreate_at());
        return memberRepository.save(member);
    }

    public Member findMemberById(Long id){
        return memberRepository.findById(id);
    }


    public Member findMemberByEmail(String email , LoginType loginType){
        return memberRepository.findByEmail(email , loginType).orElse(new Member());
    }

    @Transactional
    public void changeMemberSetting(Long memberId , UpdateMemberSettingDto dto){
        memberRepository.updateMemberSetting(memberId , dto);
    }

    @Transactional
    public void changeMemberSetting(Long memberId , String pw){
        memberRepository.updateMemberPW(memberId , pw);
    }

    @Transactional
    public void deleteMember(Long memberId ){
        memberRepository.deleteMember(memberId);
    }

    public void entityManagerClear(){
        memberRepository.clear();
    }

}
