package com.hello.animalChat.service;

import com.hello.animalChat.dto.fcm.FCMDto;
import com.hello.animalChat.error.FcmTokenException;
import com.hello.animalChat.repository.FCMRepository;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
@Transactional
class FCMServiceTest {

    @Autowired
    FCMService service;

    @Test
    void save() {
        String uuid= UUID.randomUUID().toString();
        service.save(FCMDto.builder().userId(1L).token(uuid).build());
        Assertions.assertThrows(DuplicateKeyException.class,()->service.save(FCMDto.builder().userId(1L).token(uuid).build()));
    }

    @Test
    void findByUserToken() {
        String uuid= UUID.randomUUID().toString();
        service.save(FCMDto.builder().userId(1L).token(uuid).build());
        Assertions.assertEquals(uuid , service.findByUserToken(1L));
    }

    @Test
    void deleteToken() {
        String uuid= UUID.randomUUID().toString();
        service.save(FCMDto.builder().userId(1L).token(uuid).build());
        service.deleteToken(1L);
        Assertions.assertThrows(FcmTokenException.class,()->service.findByUserToken(1L));
    }
}