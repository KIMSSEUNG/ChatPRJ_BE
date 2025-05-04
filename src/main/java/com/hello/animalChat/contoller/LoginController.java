package com.hello.animalChat.contoller;

import com.hello.animalChat.Enum.LoginType;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hello.animalChat.dto.LoginDto;
import com.hello.animalChat.dto.response.ResponseLoginDto;
import com.hello.animalChat.error.ErrorResult;
import com.hello.animalChat.service.*;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth") // 🔥 로그인 관련 API
@RequiredArgsConstructor
public class LoginController {
    
    private final LoginService loginService;
    private final FCMService fcmTokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        return ResponseEntity.ok().body(loginService.login(dto));
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseLoginDto> logout(@RequestParam Long id) {
        return ResponseEntity.ok().build();
    }

}

