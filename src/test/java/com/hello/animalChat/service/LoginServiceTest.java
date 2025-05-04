package com.hello.animalChat.service;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.dto.LoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginService service;
    @Autowired
    MemberService memberService;

    @Test
    void 로그인테스트(){
        service.login(LoginDto.builder().loginType(LoginType.GOOGLE)
                .email("tttt@naver.com")
                .password("HHHH123").build());
    }
}