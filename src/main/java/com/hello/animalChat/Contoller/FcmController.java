package com.hello.animalChat.Contoller;


import com.hello.animalChat.dto.fcm.FcmTokenDto;
import com.hello.animalChat.service.FcmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class FcmController {

    private final FcmService fcmService;

    @PostMapping("/token/regist")
    public ResponseEntity<?> registerFcmToken(@RequestBody FcmTokenDto dto) {
        fcmService.saveToken(dto);
        return ResponseEntity.ok().build();
    }

}
