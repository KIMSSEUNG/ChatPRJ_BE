package com.external.fcm;

import org.springframework.web.client.RestTemplate;

import com.hello.animalChat.dto.MessageDto;
import com.hello.animalChat.error.FcmTokenException;

import java.util.Map;

import org.springframework.http.*;

public class FcmUtil {
    
    static FcmUtil instance = new FcmUtil();

    public static FcmUtil getInstance(){
        return instance;
    }

    private FcmUtil(){}
    
    private static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";

    // 🔐 Firebase Console > Project Settings > Cloud Messaging > Server key
    private static final String SERVER_KEY = "YOUR_FIREBASE_SERVER_KEY";

    public static void sendToFcm(String token, MessageDto messageDto) {
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "key=" + SERVER_KEY);

        // 바디 설정
        Map<String, Object> message = Map.of(
            "to", token,
            "notification", Map.of(
                "title", "새로운 메시지",
                "body", "메세지가 도착 했습니다"
                //,"icon": "ic_notification"
            ),
            "data", Map.of(
                "senderId", messageDto.getSenderId().toString(),
                "message", messageDto.getMessage()
            )
        );

        // 요청 객체
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(message, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                FCM_URL, request, String.class
            );
            

            if (response.getBody().contains("NotRegistered") || response.getBody().contains("InvalidRegistration")) {
                throw new FcmTokenException("FCM 토큰이 유효하지 않음: " + token);
            }

            System.out.println("✅ FCM 전송 완료: " + response.getStatusCode());
            System.out.println("📨 FCM 응답 바디: " + response.getBody());

        } catch (Exception e) {
            System.out.println("❌ FCM 전송 실패: " + e.getMessage());
            throw new IllegalArgumentException("FCM 전송 에러");
        }
    }


}
