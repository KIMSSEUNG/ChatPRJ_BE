package com.hello.animalChat.service;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.Member;
import com.hello.animalChat.dto.MemberUpdateDto;
import com.hello.animalChat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long saveMember(Member member){
        return memberRepository.save(member);
    }

    public Member findMemberById(Long id){
        return memberRepository.findById(id);
    }


    public Member findMemberByEmail(String email , LoginType loginType){
        return memberRepository.findByEmail(email , loginType).orElse(new Member());
    }

    public void updateMember(Long memberId , MemberUpdateDto dto){
        memberRepository.updateMember(memberId , dto);
    }

    @Transactional
    public void deleteMember(Long memberId ){
        memberRepository.deleteMember(memberId);
    }

}
