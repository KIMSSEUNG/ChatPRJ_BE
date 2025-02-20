package com.hello.animalChat.service;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.Member;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class MemberServiceTest {

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
    }

    @Test
    void updateMember() {
    }

    @Test
    void deleteMember() {
    }
}