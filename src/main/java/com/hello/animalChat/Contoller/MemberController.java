package com.hello.animalChat.Contoller;

import com.hello.animalChat.domain.Member;
import com.hello.animalChat.dto.controller.RequestEmailCheckDto;
import com.hello.animalChat.dto.controller.RequestMemberDto;
import com.hello.animalChat.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/member/create")
    public ResponseEntity saveMember(@RequestBody RequestMemberDto dto){

        memberService.saveMember(dto);
        return ResponseEntity.ok().build();
    }

    //조회지만 민감 정보를 담고 있음으로 POST 대체
    @PostMapping(value = "/member/email/exist")
    public ResponseEntity EmailExist(@RequestBody RequestEmailCheckDto dto){
        Member findEmail = memberService.findMemberByEmail(dto.getEmail(), dto.getLoginType());
        if(findEmail.getId()==null)
        {
            //이메일 존재하지 않음
            return ResponseEntity.ok().build();
        }
        else
        {
            //이메일 존재함 (Error 담기)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 이메일입니다.");
        }
    }

    @PatchMapping(value = "/member/setting")
    public ResponseEntity memberSettingChange(@RequestBody RequestMemberSettingChangeDto dto)
    {
        boolean isChangeCheck = memberService.changeMemberSetting(dto);
        if(isChangeCheck)
        {
            //해당하는 아이디가 있을 경우 - 변경 후 ok 반환
            return ResponseEntity.ok().build();
        }
        else
        {
            //해당하는 아이디가 없을 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
