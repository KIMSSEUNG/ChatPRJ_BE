package com.hello.animalChat.contoller;


import com.hello.animalChat.dto.fcm.FCMDto;
import com.hello.animalChat.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class FCMController {

    private final FCMService fcmService;

    @PostMapping("/auth/fcm/token")
    public ResponseEntity regist(@RequestBody FCMDto dto) {
        try{
            fcmService.save(dto);
            return ResponseEntity.ok().build();
        }
        catch (DuplicateKeyException e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }
}
