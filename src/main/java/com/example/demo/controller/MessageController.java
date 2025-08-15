package com.example.demo.controller;

import com.example.demo.controller.dto.MessageCreateRequestDto;
import com.example.demo.controller.dto.MessageResponseDto;
import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponseDto> createMessage(@RequestBody MessageCreateRequestDto requestDto){
        MessageResponseDto message = messageService.createMessage(requestDto);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponseDto> getMessage(@PathVariable Integer id){
        MessageResponseDto messageById = messageService.getMessageById(id);
        return ResponseEntity.ok(messageById);
    }

    @GetMapping("")
    public ResponseEntity<List<MessageResponseDto>> getAllMessage(){
        List<MessageResponseDto> all = messageService.findAll();
        return ResponseEntity.ok(all);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        messageService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
