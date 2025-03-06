package com.hello.animalChat.Contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hello.animalChat.domain.Message;
import com.hello.animalChat.service.MessageService;

import java.time.LocalDateTime;
import java.util.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessageController {
    
    private final MessageService messageService;

//    @GetMapping("/new")
//    public ResponseEntity<List<Message>> getNewMessages(
//            @RequestParam Long receiverId,
//            @RequestParam LocalDateTime dateTime)
//    {
//        List<Message> messages = messageService.receiveNewMessage(receiverId, dateTime);
//        return ResponseEntity.ok(messages);
//    }



}
