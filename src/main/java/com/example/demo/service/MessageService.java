package com.example.demo.service;

import com.example.demo.controller.dto.MessageCreateRequestDto;
import com.example.demo.controller.dto.MessageResponseDto;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Message;
import com.example.demo.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public MessageResponseDto createMessage(MessageCreateRequestDto requestDto){

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(()-> new RuntimeException("유저를 조회할 수 없습니다. 유저 ID = " + requestDto.getUserId()));

        Message message = Message.create(
                requestDto.getTitle(),
                requestDto.getContent(),
                user
        );

        return MessageResponseDto.from(messageRepository.save(message));
    }


    @Transactional(readOnly = true)
    public MessageResponseDto getMessageById(Integer id){
        Message message = messageRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("메세지를 조회할 수 없습니다. 메제시 id = " + id));

        return MessageResponseDto.from(message);
    }


    @Transactional(readOnly = true)
    public List<MessageResponseDto> findAll(){
        return messageRepository.findAll()
                .stream()
                .map(MessageResponseDto::from)
                .toList();
    }

    @Transactional
    public void deleteById(Integer id){

        if(!messageRepository.existsById(id)){
            throw new RuntimeException("삭제할 메세지가 없습니다. 메세지 id  =" + id);
        }

        messageRepository.deleteById(id);
    }


}
