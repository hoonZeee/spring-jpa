package com.example.demo.controller.dto;

import com.example.demo.repository.entity.Team;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamResponseDto {
    private Integer id;
    private String teamName;
    private List<UserResponseDto> users;

    public static TeamResponseDto from(Team team){
        return new TeamResponseDto(
                team.getId(),
                team.getTeamName(),
                team.getUserList().stream()
                        .map(UserResponseDto::from)
                        .toList()
        );
    }

}
