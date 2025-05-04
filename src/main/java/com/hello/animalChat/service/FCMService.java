package com.hello.animalChat.service;

import com.hello.animalChat.dto.fcm.FCMDto;
import com.hello.animalChat.error.FcmTokenException;
import com.hello.animalChat.repository.FCMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FCMService {
    private final FCMRepository fcmTokenRepository;

    @Transactional
    public void save(FCMDto dto) {
        fcmTokenRepository.save(dto);
    }

    public String findByUserToken(Long UserId){
        try {
            return fcmTokenRepository.findByUserToken(UserId);
        }
        catch (EmptyResultDataAccessException e){
            throw new FcmTokenException("토큰이 없습니다.");
        }
    }

    //토큰이 있는 없는 에러가 발생하지 않는다.
    @Transactional
    public void deleteToken(Long UserId){
        fcmTokenRepository.deleteToken(UserId);
    }

}
