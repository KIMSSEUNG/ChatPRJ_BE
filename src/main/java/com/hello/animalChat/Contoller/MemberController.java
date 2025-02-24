package com.hello.animalChat.Contoller;

import com.hello.animalChat.dto.controller.RequestMemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("/api")
public class MemberController {

    @PostMapping(value = "/create")
    public ResponseEntity saveMember(@RequestBody RequestMemberDto dto){
        System.out.println(dto.toString());
        return ResponseEntity.ok().build();
    }

}
