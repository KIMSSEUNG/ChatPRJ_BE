package com.hello.animalChat.Contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.external.fcm.FcmUtil;
import com.hello.animalChat.dto.controller.LoginDto;
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
    private final FcmTokenService fcmTokenService;
    
    @PostMapping("/login/basic")
    public ResponseEntity<ResponseLoginDto> loginBasic(@RequestBody LoginDto dto) {
        ResponseLoginDto resDto = loginService.loginBasic(dto);
        return ResponseEntity.ok().body(resDto);
    }

    @PostMapping("/login/google")
    public ResponseEntity<ResponseLoginDto> loginGoogle(@RequestBody LoginDto dto) {
        ResponseLoginDto resDto = loginService.loginGoogle(dto);
        return ResponseEntity.ok().body(resDto);
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseLoginDto> logout(@RequestParam Long id) {
        //토큰제거
        fcmTokenService.deleteToken(id);
        
        return ResponseEntity.ok().build();
    }


    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorResult>  NoResultExHandle(NoResultException e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResult(HttpStatus.BAD_REQUEST.value(), "이메일 또는 비밀번호가 틀렸습니다."));
    }

    
}

