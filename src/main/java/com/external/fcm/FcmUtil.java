package com.external.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.client.RestTemplate;

import com.hello.animalChat.dto.message.MessageDto;
import com.hello.animalChat.error.FcmTokenException;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.*;

public class FcmUtil {
    
    static FcmUtil instance = new FcmUtil();

    public static FcmUtil getInstance(){
        return instance;
    }

    private FcmUtil(){}

    private static final String PROJECT_ID = "animalprj-6ade0"; // 예: animalchat-xxxx
    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String FCM_ENDPOINT = "https://fcm.googleapis.com/v1/projects/%s/messages:send";
    private static final String SERVICE_ACCOUNT_PATH = "src/main/resources/firebase/service-account-key.json"; // 경로 주의

    public static void sendToFcm(String token, MessageDto messageDto , LocalDateTime time) {
        try {
            // ✅ 1. AccessToken 얻기
            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(new FileInputStream(SERVICE_ACCOUNT_PATH))
                    .createScoped(Collections.singleton(MESSAGING_SCOPE));
            credentials.refreshIfExpired();
            String accessToken = credentials.getAccessToken().getTokenValue();

            // ✅ 2. 메시지 JSON 구성
            JsonObject data = new JsonObject();
            data.addProperty("senderId", messageDto.getSenderId().toString());
            data.addProperty("message", messageDto.getMessage());
            data.addProperty("time", time.toString());

            JsonObject message = new JsonObject();
            message.addProperty("token", token);
            message.add("data", data);


            JsonObject finalMessage = new JsonObject();
            finalMessage.add("message", message);

            // ✅ 3. HTTP 요청
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken); // Authorization: Bearer ...

            HttpEntity<String> request = new HttpEntity<>(new Gson().toJson(finalMessage), headers);

            RestTemplate restTemplate = new RestTemplate();
            String fcmUrl = String.format(FCM_ENDPOINT, PROJECT_ID);

            ResponseEntity<String> response = restTemplate.postForEntity(fcmUrl, request, String.class);

            System.out.println("✅ 응답 코드: " + response.getStatusCode());
            System.out.println("📨 응답 바디: " + response.getBody());

        } catch (Exception e) {
            System.err.println("❌ FCM 전송 실패: " + e.getMessage());
            throw new RuntimeException("FCM 메시지 전송 중 오류 발생", e);
        }
    }

}