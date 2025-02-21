package com.hello.animalChat.service;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootTest
@Transactional
public class MessageServiceTest {

    private final MemberService memberService;
    
    @BeforeEach
    public void Init(){
        Member member = new Member(null , LoginType.GOOGLE, "tmdgh12345" ,"12345","ENFG","고양이",'남',
                LocalDateTime.now());
        Member member2 = new Member(null , LoginType.GOOGLE, "tkdgh5" ,"123335","ETTT","고양이",'여',
                LocalDateTime.now());
        memberService.saveMember(member);
        memberService.saveMember(member2);
    }
    
    @Test
    public void save(){

    }
    
    @Test
    public void receiveMessage(){
        
    } 
    
}
