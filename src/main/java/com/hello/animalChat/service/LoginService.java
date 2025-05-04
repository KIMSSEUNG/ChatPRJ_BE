package com.hello.animalChat.service;

import org.springframework.stereotype.Service;

import com.hello.animalChat.dto.LoginDto;
import com.hello.animalChat.dto.response.ResponseLoginDto;
import com.hello.animalChat.repository.LoginRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    
    private final LoginRepository LoginRepository;

    public ResponseLoginDto login(LoginDto dto){
        return LoginRepository.login(dto);
    }

}
