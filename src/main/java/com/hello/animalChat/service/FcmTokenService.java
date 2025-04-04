package com.hello.animalChat.service;

import org.springframework.stereotype.Service;

import com.hello.animalChat.dto.controller.FcmTokenDto;
import com.hello.animalChat.repository.FcmTokenRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FcmTokenService {
    
    private final FcmTokenRepository fcmTokenRepository;

    @Transactional
    public void save(FcmTokenDto dto) {
        fcmTokenRepository.save(dto);
    }

    public String findByUserToken(Long UserId){
        return fcmTokenRepository.findByUserToken(UserId);
    }

    @Transactional
    public void deleteToken(Long UserId){
        fcmTokenRepository.deleteToken(UserId);
    }
    
}