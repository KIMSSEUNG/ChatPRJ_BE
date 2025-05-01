package com.hello.animalChat.Contoller;

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
@RequestMapping("/api")
@Slf4j
public class MessageController {
    
    private final MessageService messageService;
    private final FcmTokenService fcmTokenService;

    @PostMapping("/message/new")
    public ResponseEntity getNewMessages(@RequestBody NewMessageDto dto)
    {
        try{

            System.out.println(dto.toString());
            //랜덤하게 보내줄 사람 지정
            Long recieveId = messageService.RandomId(dto);

            if(recieveId==0L){
                return ResponseEntity.ok().build();
            }
            //메세지 저장
            MessageDto messageDto = new MessageDto(dto.getSenderId() , recieveId , dto.getMessage());
            messageService.savaMessage(messageDto);

            ///받은 메세지 FCM 클라우드에 전송
            // 토큰 받기
            String token = fcmTokenService.findByUserToken(recieveId);
            // 토큰을 통한 데이터 전송
            FcmUtil.getInstance().sendToFcm(token, messageDto);
            return ResponseEntity.ok().build();

        }catch(FcmTokenException e){
            // 커스텀 예외: FCM 토큰이 잘못되었거나 만료됨
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(440)).build();
        }
        
    }

    @PostMapping("/message/send")
    public ResponseEntity getSendMessages(@RequestBody MessageDto dto)
    {
        try{
            //메세지 저장
            messageService.savaMessage(dto);

            ///받은 메세지 FCM 클라우드에 전송
            // 토큰 받기
            String token = fcmTokenService.findByUserToken(dto.getReceiverId());
            // 토큰을 통한 데이터 전송
            FcmUtil.getInstance().sendToFcm(token, dto);
            return ResponseEntity.ok().build();

        }catch(FcmTokenException e){
            // 커스텀 예외: FCM 토큰이 잘못되었거나 만료됨
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(440)).build();
        }

    }

    @PostMapping("/message/all")
    public ResponseEntity getAllMessages(@RequestBody Long dto)
    {
        //모든 메세지 추출
        List<MessageDetailDto> receiveMessage = messageService.receiveMessage(dto);
        return ResponseEntity.ok().body(receiveMessage);
    }



}
