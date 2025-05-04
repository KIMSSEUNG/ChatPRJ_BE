package com.hello.animalChat.contoller;

import com.hello.animalChat.Enum.LoginType;
import com.hello.animalChat.domain.member.Member;
import com.hello.animalChat.dto.member.MemberDto;
import com.hello.animalChat.dto.member.MemberPWChangeDto;
import com.hello.animalChat.dto.member.MemberSettingChangeDto;
import com.hello.animalChat.service.MemberService;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //@PostMapping(value = "/member/create")
    @PostMapping
    public ResponseEntity saveMember(@RequestBody MemberDto dto){
        try {
            Long id = memberService.saveMember(dto);
            return ResponseEntity.ok().body(Map.of("id",id));
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(409).build();
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findMemberById(@PathVariable Long id){
        try {
            memberService.findMemberById(id);
            return ResponseEntity.ok().build();
        }catch (NoResultException e){
            return ResponseEntity.status(404).build();
        }

    }

    //@PostMapping(value = "/member/email/exist")
    @GetMapping(value = "/email")
    //public ResponseEntity EmailExist(@RequestBody EmailCheckDto dto){
    public ResponseEntity findMemberByEmail(
            @RequestParam String email
            , @RequestParam LoginType loginType){
        try {
            memberService.findMemberByEmail(email, loginType);
            return ResponseEntity.ok().build();
        }catch (NoResultException e){
            return ResponseEntity.status(404).build();
        }
    }

    @PatchMapping(value = "/setting")
    public ResponseEntity memberSettingChange(@RequestBody MemberSettingChangeDto dto)
    {
        if(memberService.changeMemberSetting(dto))
        {
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.status(404).build();
        }
    }

    @PatchMapping(value = "/password")
    public ResponseEntity memberPWChange(@RequestBody MemberPWChangeDto dto)
    {
        if(memberService.changeMemberPW(dto.getId(), dto.getChangePW()))
        {
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity memberDelete(@PathVariable Long id)
    {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }

}
