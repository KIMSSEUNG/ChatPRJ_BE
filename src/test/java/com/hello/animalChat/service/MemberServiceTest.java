package com.hello.animalChat.service;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.Member;
import com.hello.animalChat.dto.UpdateMemberSettingDto;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    Long testID=0L;
    
    @BeforeEach
    void beforeEach() {
        Member member = new Member(null , LoginType.GOOGLE, "tmdgh12345" ,"12345","ENFG","고양이",'남',
                LocalDateTime.now());
        memberService.saveMember(member);
        testID = member.getId();
    }
    
    
    @Autowired
    MemberService memberService;

    @Test
    void saveMember() {
        Member member = new Member(null , LoginType.GOOGLE, "tmdgh" ,"12345","ENFG","고양이",'남',
                LocalDateTime.now());
        Long id = memberService.saveMember(member);

        Member find = memberService.findMemberById(id);
        Assertions.assertThat(member.getId()).isEqualTo(find.getId());

    }

    @Test
    void findMemberByEmail() {
        Member find = memberService.findMemberByEmail("tmdgh12345",LoginType.GOOGLE);
        Assertions.assertThat(find.getEmail()).isEqualTo("tmdgh12345");
        //없을 경우
        Member find2 = memberService.findMemberByEmail("gh12345",LoginType.GOOGLE);
        Assertions.assertThat(find2.getEmail()).isEqualTo(null);

    }

    @Test
    void updateMember() {
        UpdateMemberSettingDto dto = new UpdateMemberSettingDto("ENFJ", "cat", '여');
        Long id = 1L;
        memberService.changeMemberSetting(id, dto);
        memberService.entityManagerClear();
        Member find = memberService.findMemberById(id);
        Assertions.assertThat(find.getAnimal()).isEqualTo("cat");
    }

    @Test
    void deleteMember() {
        System.out.println("delete Test");
        Member m = memberService.findMemberById(testID);
        Assertions.assertThat(m.getEmail()).isEqualTo("tmdgh12345");
        memberService.deleteMember(testID);
        Member m2 = memberService.findMemberById(testID);
        Assertions.assertThat(m2).isEqualTo(null);
        
    }
}