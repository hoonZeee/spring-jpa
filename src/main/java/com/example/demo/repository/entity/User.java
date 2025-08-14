package com.example.demo.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private String job;
    private String specialty;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="team_id")
    private Team team;

    public static User create(String username, String password, String name, Integer age, String job, String specialty, Team team) {
        return new User(
                null,
                username,
                password,
                name,
                age,
                job,
                specialty,
                LocalDateTime.now(),
                team
        );
    }
}
