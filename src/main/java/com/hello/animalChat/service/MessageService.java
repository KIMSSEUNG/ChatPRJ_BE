package com.hello.animalChat.service;
import com.hello.animalChat.domain.Member;
import com.hello.animalChat.domain.Message;
import com.hello.animalChat.dto.MessageDto;
import com.hello.animalChat.dto.response.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


    

    //회원의 모든 메세지를 찾기 위한 기능
    public List<MessageDetailDto> receiveMessage(Long id){
        
        //메세지 도메인 얻어옴
        List<Message> findMessage = messageRepository.findByReceiveAllMessage(id);

        //맵으로 만든이유
        //중복되는 MessageDetailDto를 없애기 위해 키 값을 넣어서 중복 방지
        Map<Long , MessageDetailDto> result = new HashMap<>();
        for(int i=0;i<findMessage.size();i++){
            Message m = findMessage.get(i);
            if(result.containsKey(m.getSender().getId())){
                result.get(m.getSender().getId()).getMessage().add(m.getMessage());
            }
            else{
                result.put(m.getSender().getId() , new MessageDetailDto(
                        m.getSender().getId() ,
                        m.getSender().getMbti() ,
                        m.getSender().getAnimal() ,
                        m.getSender().getGender() ,
                        new ArrayList<>(Arrays.asList(m.getMessage()))
                ));
            }
        }

        return new ArrayList<>(result.values());
    }


    //Polling 방식이면 해당하는 것으로 사용해야된다. (old)
    public Map<Long , MessageDetailDto> receiveNewMessage(Long receiveId , LocalDateTime lastReceiveTime){
        List<Message> findMessage = messageRepository.findByNewMessage(receiveId ,  lastReceiveTime);

        Map<Long , MessageDetailDto> result = new HashMap<>();
        for(int i=0;i<findMessage.size();i++){
            Message m = findMessage.get(i);
            if(result.containsKey(m.getSender().getId())){
                result.get(m.getSender().getId()).getMessage().add(m.getMessage());
            }
            else{
                result.put(m.getSender().getId() , new MessageDetailDto(
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

    public void savaMessage(MessageDto dto) {
        Member sender = memberRepository.findById(dto.getSenderId());
        Member receiver = memberRepository.findById(dto.getReceiverId());
        
        Message message =  new Message(sender,receiver,dto.getMessage(),LocalDateTime.now());

        messageRepository.save(message);
    }
    
}
