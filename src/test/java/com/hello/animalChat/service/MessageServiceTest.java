package com.hello.animalChat.service;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.Message;
import com.hello.animalChat.domain.member.Member;
import com.hello.animalChat.dto.message.MessageDto;
import com.hello.animalChat.dto.message.NewMessageDto;
import com.hello.animalChat.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(propagation = Propagation.REQUIRES_NEW)
class MessageServiceTest {

    @Autowired
    private MessageService messageService;
    @Autowired
    private MemberRepository memberRepository;
    private Long senderId=0L;
    private Long receiveId=0L;

    @BeforeEach
    void init(){
        for (int i = 1; i <= 20; i++) {
            Member member = Member.builder()
                    .loginType(LoginType.BASIC)
                    .email("user" + i + "@example.com")
                    .password("password" + i)
                    .name("테스트유저" + i)
                    .age(20 + i)
                    .mbti("INTJ")
                    .animal("고양이")
                    .gender('M')
                    .create_at(LocalDateTime.now())
                    .build();

            Long id = memberRepository.save(member);
            if(senderId==0L)senderId=id;
            else if(receiveId==0L)receiveId=id;
            //System.out.println(id);
        }

        for (int i = 0; i < 20; i++) {
            Long receiverId=0L;
            if(i<10){
                receiverId = senderId+1L;
            }
            else{
                receiverId = senderId+2L;
            }
            Long senderId;
            if(i+1==5){
                senderId = (long) (4);
            }
            else{
                senderId = (long) (i + 1);
            }
            MessageDto dto = new MessageDto(
                    senderId,
                    receiverId,
                    "안녕하세요! 테스트 메시지입니다. [" + (i + 1) + "]"
            );

            LocalDateTime time = LocalDateTime.now();
            Long messageId = messageService.savaMessage(dto, time);
        }
    }

    @Test
    void savaMessage() {
        System.out.println(senderId+" "+receiveId);
        MessageDto dto = new MessageDto(senderId,receiveId,"안녕");
        Long id = messageService.savaMessage(dto , LocalDateTime.now());
        Message find = messageService.findById(id);
        Assertions.assertEquals("안녕",find.getMessage());

        MessageDto dto2 = new MessageDto(senderId*50,receiveId*50,"안녕");
        Assertions.assertThrows(ResponseStatusException.class,()->messageService.savaMessage(dto2 , LocalDateTime.now()));
    }

    @Test
    void receiveMessage() {
        //1L이 보낸 총 메세지 수
        Assertions.assertEquals(1,messageService.receiveMessage(1L).get(0).getMessage().size());
        //보낸사람
        Assertions.assertEquals(9,messageService.receiveMessage(1L).size());
        Assertions.assertEquals(1,messageService.receiveMessage(2L).get(0).getMessage().size());
    }

    @Test
    void randomId() {
        Long receiveId = messageService.randorMatch(
                NewMessageDto.builder().senderId(5L).maxAge(30).minAge(20).gender('M').build());

        Assertions.assertNotEquals(0L ,receiveId );
        System.out.println(receiveId);
        Assertions.assertEquals(0L,messageService.randorMatch(
                NewMessageDto.builder().senderId(1L).maxAge(30).minAge(20).gender('F').build()));

    }

}