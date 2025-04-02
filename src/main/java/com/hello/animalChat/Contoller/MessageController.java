package com.hello.animalChat.Contoller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.external.fcm.FcmUtil;
import com.hello.animalChat.dto.MessageDto;
import com.hello.animalChat.service.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessageController {
    
    private final MessageService messageService;
    private final FcmTokenService fcmTokenService;

//    @GetMapping("/new")
//    public ResponseEntity<List<Message>> getNewMessages(
//            @RequestParam Long receiverId,
//            @RequestParam LocalDateTime dateTime)
//    {
//        List<Message> messages = messageService.receiveNewMessage(receiverId, dateTime);
//        return ResponseEntity.ok(messages);
//    }

    @GetMapping("/message/send")
    public ResponseEntity getNewMessages(@RequestBody MessageDto dto)
    {
        try{
            //메세지 저장
            messageService.savaMessage(dto);

            ///받은 메세지 FCM 클라우드에 전송
            // 토큰 받기
            String token = fcmTokenService.findByUserToken(dto.getReceiverId());

            // 토큰을 통한 데이터 전송
            FcmUtil.getInstance().sendToFcm(token, dto);

        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }    
        return ResponseEntity.ok().build();
    }



}
