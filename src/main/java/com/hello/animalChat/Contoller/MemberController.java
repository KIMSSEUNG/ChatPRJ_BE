package com.hello.animalChat.Contoller;

import com.hello.animalChat.domain.Member;
import com.hello.animalChat.dto.controller.*;
import com.hello.animalChat.service.FcmTokenService;
import com.hello.animalChat.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final FcmTokenService fcmTokenService;

    @PostMapping(value = "/member/create")
    public String saveMember(@RequestBody RequestMemberDto dto){
        System.out.println(dto.toString());
        Long id = memberService.saveMember(dto);
        return id.toString();
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
            //해당하는 아이디가 있을 경우 -> 변경 후 ok 반환
            return ResponseEntity.ok().build();
        }
        else
        {
            //해당하는 아이디가 없을 경우 -> NotFound Error 발송
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping(value = "/member/pw")
    public ResponseEntity memberPWChange(@RequestBody RequestMemberPWChangeDto dto)
    {
        boolean isChangeCheck = memberService.changeMemberPW(dto.getId(), dto.getChangePW());
        if(isChangeCheck)
        {
            //해당하는 아이디가 있을 경우 -> PW 변경 후 ok 반환
            return ResponseEntity.ok().build();
        }
        else
        {
            //해당하는 아이디가 없을 경우 -> NotFound Error 발송
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/member/delete")
    public ResponseEntity memberDelete(@RequestBody Long deleteId)
    {
        //멤버 delete
        memberService.deleteMember(deleteId);
        
        //토큰 삭제
        fcmTokenService.deleteToken(deleteId);

        return ResponseEntity.ok().build();
    }

}
