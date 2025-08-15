package com.example.demo.controller.dto;

import com.example.demo.repository.entity.Message;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MessageResponseDto {
    private Integer id;
    private String title;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private UserSimpleResponseDto createdBy;

    public static MessageResponseDto from(Message message){
        return new MessageResponseDto(
                message.getId(),
                message.getTitle(),
                message.getContent(),
                message.getCreatedAt(),
                UserSimpleResponseDto.from(message.getCreatedBy())
        );
    }
}
