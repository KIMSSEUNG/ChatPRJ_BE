package com.hello.animalChat.Contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hello.animalChat.dto.controller.LoginDto;
import com.hello.animalChat.service.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth") // 🔥 로그인 관련 API
@RequiredArgsConstructor
public class LoginController {
    
    private final LoginService loginService;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto dto) {
        loginService.login(dto);
    }
    
}

