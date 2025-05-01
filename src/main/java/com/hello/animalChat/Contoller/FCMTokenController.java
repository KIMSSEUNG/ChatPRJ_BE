package com.hello.animalChat.Contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.animalChat.dto.FcmTokenDto;
import com.hello.animalChat.service.FcmTokenService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class FCMTokenController {
    
    private final FcmTokenService fcmTokenService;

    @PostMapping("/fcm/token/regist")
    public ResponseEntity tokenSave(@RequestBody FcmTokenDto dto) {
        try{
            fcmTokenService.save(dto);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

    }


}
