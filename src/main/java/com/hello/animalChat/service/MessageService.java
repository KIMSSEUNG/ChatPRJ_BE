package com.hello.animalChat.service;
import com.hello.animalChat.domain.Member;
import com.hello.animalChat.domain.Message;
import com.hello.animalChat.dto.response.*;
import org.springframework.stereotype.Service;
import java.util.*;

import com.hello.animalChat.repository.MemberRepository;
import com.hello.animalChat.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    public Map<Long,ReceiveMessageResponseDto> receiveMessage(Long Id){
        List<Message> findMessage = messageRepository.findByReceiveMessage(Id);
        
        if(findMessage.size()==0)return new HashMap();

        Map<Long,ReceiveMessageResponseDto> result = new HashMap();

        for(int i=0;i<findMessage.size();i++){
            Message m = findMessage.get(i);
            if(result.containsKey(m.getSenderId())){
                ReceiveMessageResponseDto dto = result.get(m.getSenderId());
                dto.getMessage().add(m.getMessage());
            }
            else{
                Member merber = memberRepository.findById(m.getSenderId());
                List<String> messageList = new ArrayList<>();
                messageList.add(m.getMessage());
                result.put(m.getSenderId(), new ReceiveMessageResponseDto(m.getSenderId(), merber.getMbti(), merber.getAnimal(), merber.getGender(),messageList));
            }
        }

        return result;
    } 
    
}
