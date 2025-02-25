package com.hello.animalChat.service;
import java.time.LocalDateTime;
import java.util.Map;

import com.hello.animalChat.domain.Message;
import com.hello.animalChat.dto.response.ReceiveMessageResponseDto;
import com.hello.animalChat.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.Member;

@SpringBootTest
@Transactional
public class MessageServiceTest {

    @Autowired private MessageService messageService;
    @Autowired private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    private Long member1_id=0L;
    private Long member2_id=0L;

    @BeforeEach
    public void Init(){
        Member member = new Member( LoginType.GOOGLE, "tmdgh12345" ,"12345","ENFG","고양이",'남',
                LocalDateTime.now());
        Member member2 = new Member( LoginType.GOOGLE, "tkdgh5" ,"123335","ETTT","고양이",'여',
                LocalDateTime.now());
        memberRepository.save(member);
        member1_id = member.getId();
        memberRepository.save(member2);
        member2_id = member2.getId();
    }
    
    @Test
    public void saveAndReceiveMessage(){
        Member m1 = memberService.findMemberById(member1_id);
        Member m2 = memberService.findMemberById(member2_id);

        for(int i=0;i<10;i++){
            Message message1 = new Message(m1 , m2 , "1번이 2번에게 메세지 전송 "+i,LocalDateTime.now());
            Message message2 = new Message(m2 , m1 , "2번이 1번에게 메세지 전송 "+i,LocalDateTime.now());
            messageService.savaMessage(message1);
            messageService.savaMessage(message2);
        }

        Map<Long, ReceiveMessageResponseDto> map1 = messageService.receiveMessage(m1);
        Map<Long, ReceiveMessageResponseDto> map2 = messageService.receiveMessage(m2);

        Assertions.assertThat(map1.get(m2.getId()).getMessage().size()).isEqualTo(10);
        Assertions.assertThat(map2.get(m1.getId()).getMessage().size()).isEqualTo(10);
    }
    
}
