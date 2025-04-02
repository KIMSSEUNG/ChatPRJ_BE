package com.hello.animalChat.service;

import org.springframework.stereotype.Service;

import com.hello.animalChat.dto.controller.FcmTokenDto;
import com.hello.animalChat.repository.FcmTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FcmTokenService {
    
    private final FcmTokenRepository fcmTokenRepository;

    public void save(FcmTokenDto dto) {
        fcmTokenRepository.save(dto);

    }

    public String findByUserToken(Long UserId){
        return fcmTokenRepository.findByUserToken(UserId);
    }

    public void deleteToken(Long UserId){
        fcmTokenRepository.deleteToken(UserId);
    }
    
}