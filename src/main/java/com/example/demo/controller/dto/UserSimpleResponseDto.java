package com.example.demo.controller.dto;

import com.example.demo.repository.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSimpleResponseDto {
    private Integer id;
    private String userName;
    private String name;

    public static UserSimpleResponseDto from(User user){
        return new UserSimpleResponseDto(
                user.getId(),
                user.getUsername(),
                user.getName()
        );
    }
}
