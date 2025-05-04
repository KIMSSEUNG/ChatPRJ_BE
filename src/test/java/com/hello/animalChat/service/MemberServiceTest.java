package com.hello.animalChat.service;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.member.Member;
import com.hello.animalChat.dto.member.MemberDto;
import com.hello.animalChat.dto.member.MemberSettingChangeDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    EntityManager em;


    @Test
    void saveMember() {
        MemberDto m = MemberDto.builder()
                .loginType(LoginType.BASIC)     // 예: BASIC, GOOGLE 등
                .email("example@example.com")
                .password("securePassword123")
                .name("홍길동")
                .age(25)
                .mbti("INTJ")
                .animal("고양이")
                .token("abc123xyzToken")
                .gender('M')
                .create_at(LocalDateTime.now())
                .build();

        memberService.saveMember(m);
        Assertions.assertThrows( DataIntegrityViolationException.class ,() -> memberService.saveMember(m));

    }

    @Test
    void findMemberById() {
        MemberDto m = MemberDto.builder()
                .loginType(LoginType.BASIC)     // 예: BASIC, GOOGLE 등
                .email("example@example.com")
                .password("securePassword123")
                .name("홍길동")
                .age(25)
                .mbti("INTJ")
                .animal("고양이")
                .token("abc123xyzToken")
                .gender('M')
                .create_at(LocalDateTime.now())
                .build();

        Long id = memberService.saveMember(m);
        Assertions.assertEquals(id , memberService.findMemberById(id).getId());
        Assertions.assertThrows(NoResultException.class,()->memberService.findMemberById(id*50));

    }

    @Test
    void findMemberByEmail() {
        MemberDto m = MemberDto.builder()
                .loginType(LoginType.BASIC)     // 예: BASIC, GOOGLE 등
                .email("example@example.com")
                .password("securePassword123")
                .name("홍길동")
                .age(25)
                .mbti("INTJ")
                .animal("고양이")
                .token("abc123xyzToken")
                .gender('M')
                .create_at(LocalDateTime.now())
                .build();

        Long id = memberService.saveMember(m);
        Assertions.assertEquals("example@example.com" , memberService.findMemberByEmail("example@example.com" , LoginType.BASIC).getEmail());
        Assertions.assertThrows(NoResultException.class,()->memberService.findMemberById(id*50));
    }

    @Test
    void findReferenceById(){
        MemberDto m = MemberDto.builder()
                .loginType(LoginType.BASIC)     // 예: BASIC, GOOGLE 등
                .email("example@example.com")
                .password("securePassword123")
                .name("홍길동")
                .age(25)
                .mbti("INTJ")
                .animal("고양이")
                .token("abc123xyzToken")
                .gender('M')
                .create_at(LocalDateTime.now())
                .build();

        Long id = memberService.saveMember(m);
        em.clear();
        // reference 확인
        //Assertions.assertEquals(id , memberService.findMemberById(id).getId());
        Assertions.assertEquals(id , memberService.findReferenceById(id).getId());

        Assertions.assertThrows(EntityNotFoundException.class,()->memberService.findReferenceById(id*50).getEmail());
    }


    @Test
    void changeMemberSetting() {
        MemberDto m = MemberDto.builder()
                .loginType(LoginType.BASIC)     // 예: BASIC, GOOGLE 등
                .email("example@example.com")
                .password("securePassword123")
                .name("홍길동")
                .age(25)
                .mbti("INTJ")
                .animal("고양이")
                .token("abc123xyzToken")
                .gender('M')
                .create_at(LocalDateTime.now())
                .build();

        Long id = memberService.saveMember(m);
        MemberSettingChangeDto dto = MemberSettingChangeDto.builder()
                .changeId(id)
                .animal("곰")
                .mbti("INFJ").build();
        memberService.changeMemberSetting(dto);
        Member find =memberService.findMemberById(id);

        Assertions.assertEquals("곰" , find.getAnimal());
        dto.setChangeId(id*50);
        Assertions.assertEquals(false, memberService.changeMemberSetting(dto));
    }

    @Test
    void changeMemberPW() {
        MemberDto m = MemberDto.builder()
                .loginType(LoginType.BASIC)     // 예: BASIC, GOOGLE 등
                .email("example@example.com")
                .password("securePassword123")
                .name("홍길동")
                .age(25)
                .mbti("INTJ")
                .animal("고양이")
                .token("abc123xyzToken")
                .gender('M')
                .create_at(LocalDateTime.now())
                .build();

        Long id = memberService.saveMember(m);
        memberService.changeMemberPW(id,"1234");
        Member find =memberService.findMemberById(id);
        Assertions.assertEquals("1234" , find.getPassword());
    }

    @Test
    void deleteMember() {

        MemberDto m = MemberDto.builder()
                .loginType(LoginType.BASIC)     // 예: BASIC, GOOGLE 등
                .email("example@example.com")
                .password("securePassword123")
                .name("홍길동")
                .age(25)
                .mbti("INTJ")
                .animal("고양이")
                .token("abc123xyzToken")
                .gender('M')
                .create_at(LocalDateTime.now())
                .build();

        Long id = memberService.saveMember(m);
        memberService.deleteMember(id);
        Assertions.assertThrows(NoResultException.class,()->memberService.findMemberById(id));
    }

}