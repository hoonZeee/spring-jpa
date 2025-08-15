package com.example.demo.controller.dto;

import com.example.demo.repository.entity.Team;
import com.example.demo.repository.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {
    private Integer id;
    private String username;
    private String name;
    private String job;
    private String specialty;
    private Integer teamId;
    private List<MessageResponseDto> messageList;

    public static UserResponseDto from(User entity) {

        Integer teamId = Optional.ofNullable(entity.getTeam())
                .map(Team::getId)
                .orElse(null);

        List<MessageResponseDto> messages = Optional.ofNullable(entity.getMessages())
                .orElse(Collections.emptyList())
                .stream()
                .map(MessageResponseDto::from)
                .toList();

        return new UserResponseDto(
                entity.getId(),
                entity.getUsername(),
                entity.getName(),
                entity.getJob(),
                entity.getSpecialty(),
                teamId,
                messages
        );
    }
}