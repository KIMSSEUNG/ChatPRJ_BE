package com.hello.animalChat.contoller;

import java.time.LocalDateTime;
import java.util.*;

import com.hello.animalChat.dto.message.NewMessageDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.external.fcm.FcmUtil;
import com.hello.animalChat.dto.message.MessageDto;
import com.hello.animalChat.dto.response.MessageDetailDto;
import com.hello.animalChat.error.FcmTokenException;
import com.hello.animalChat.service.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
@Slf4j
public class MessageController {
    
    private final MessageService messageService;
    private final FCMService fcmService;

    //@PostMapping("/message/new")
    @PostMapping("/new")
    public ResponseEntity getNewMessages(@RequestBody NewMessageDto dto)
    {
        try{

            System.out.println(dto.toString());
            //랜덤하게 보내줄 사람 지정
            Long recieveId = messageService.randorMatch(dto);

            if(recieveId==0L){
                return ResponseEntity.ok().build();
            }
            MessageDto messageDto = new MessageDto(dto.getSenderId() , recieveId , dto.getMessage());
            LocalDateTime time = LocalDateTime.now();
            ///받은 메세지 FCM 클라우드에 전송
            String token = fcmService.findByUserToken(recieveId);
            FcmUtil.getInstance().sendToFcm(token, messageDto ,time );
            //메세지 저장
            messageService.savaMessage(messageDto,time);
            return ResponseEntity.ok().build();

        }catch(FcmTokenException e){
            // 커스텀 예외: FCM 토큰이 잘못되었거나 만료됨
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(440)).build();
        }
        
    }

    @PostMapping
    public ResponseEntity getSendMessages(@RequestBody MessageDto dto)
    {
        try{
            LocalDateTime time = LocalDateTime.now();
            String token = fcmService.findByUserToken(dto.getReceiverId());
            // 토큰을 통한 데이터 전송
            FcmUtil.getInstance().sendToFcm(token, dto ,time);
            //데이터 저장
            messageService.savaMessage(dto,time);
            return ResponseEntity.ok().build();

        }catch(FcmTokenException e){
            // 커스텀 예외: FCM 토큰이 잘못되었거나 만료됨
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(440)).build();
        }

    }

    //@PostMapping("/message/all")
    @GetMapping
    public ResponseEntity getAllMessages(@RequestBody Long dto)
    {
        //모든 메세지 추출
        List<MessageDetailDto> receiveMessage = messageService.receiveMessage(dto);
        return ResponseEntity.ok().body(receiveMessage);
    }



}
