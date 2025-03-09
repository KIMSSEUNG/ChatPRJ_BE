package com.hello.animalChat.service;

import org.springframework.stereotype.Service;

import com.hello.animalChat.dto.controller.LoginDto;
import com.hello.animalChat.dto.response.ResponseLoginDto;
import com.hello.animalChat.repository.LoginRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    
    private final LoginRepository LoginRepository;

    public ResponseLoginDto loginBasic(LoginDto dto){
        return LoginRepository.loginBasic(dto);
    }

    public ResponseLoginDto loginGoogle(LoginDto dto){
        return LoginRepository.loginGoogle(dto);
    }
}
