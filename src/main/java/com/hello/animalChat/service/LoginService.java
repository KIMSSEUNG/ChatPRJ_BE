package com.hello.animalChat.service;

import org.springframework.stereotype.Service;

import com.hello.animalChat.dto.controller.LoginDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    
    private final LoginRepository LoginRepository;

    public void login(LoginDto dto){
        try{
            LoginRepository.login(dto);
        }
        catch{

        }
        catch{
            
        }
        
    }



}
