package com.hello.animalChat.service;
import com.hello.animalChat.domain.Member;
import com.hello.animalChat.domain.Message;
import com.hello.animalChat.dto.response.*;
import org.springframework.stereotype.Service;
import java.util.*;

import com.hello.animalChat.repository.MemberRepository;
import com.hello.animalChat.repository.MessageRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    public void savaMessage(Message message){
        messageRepository.save(message);
    }

    public Map<Long , ReceiveMessageResponseDto> receiveMessage(Member member){
        List<Message> findMessage = messageRepository.findByReceiveMessage(member.getId());

        Map<Long , ReceiveMessageResponseDto> result = new HashMap<>();
        for(int i=0;i<findMessage.size();i++){
            Message m = findMessage.get(i);
            if(result.containsKey(m.getSender().getId())){
                result.get(m.getSender().getId()).getMessage().add(m.getMessage());
            }
            else{
                result.put(m.getSender().getId() , new ReceiveMessageResponseDto(
                        m.getSender().getId() ,
                        m.getSender().getMbti() ,
                        m.getSender().getAnimal() ,
                        m.getSender().getGender() ,
                        new ArrayList<>(Arrays.asList(m.getMessage()))
                ));
            }
        }

        return result;
    } 
    
}
