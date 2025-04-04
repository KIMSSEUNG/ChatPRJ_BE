package com.hello.animalChat.service;

import com.hello.animalChat.dto.fcm.FcmTokenDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FcmService {
    private final Map<Long, String> tokenStore = new ConcurrentHashMap<>();

    public void saveToken(FcmTokenDto dto) {
        tokenStore.put(dto.getUserId(), dto.getToken());
        System.out.println("✅ 저장된 FCM 토큰: " + dto.getUserId() + " → " + dto.getToken());
    }

    public String getToken(Long userId) {
        return tokenStore.get(userId);
    }

}
