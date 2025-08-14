package com.example.demo.service;

import com.example.demo.controller.dto.TeamCreateRequestDto;
import com.example.demo.controller.dto.TeamResponseDto;
import com.example.demo.repository.TeamRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Transactional
    public TeamResponseDto findById(Integer id){
        Team team = teamRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("팀이 존재하지 않습니다." + id));
        return TeamResponseDto.from(team);
    }

    @Transactional
    public List<TeamResponseDto> findAll(){
        return teamRepository.findAll()
                .stream()
                .map(TeamResponseDto::from)
                .toList();
    }

    @Transactional
    public TeamResponseDto save(TeamCreateRequestDto requestDto){
        Team team = Team.create(
                requestDto.getTeamName()
        );
        Team crated = teamRepository.save(team);
        return TeamResponseDto.from(crated);
    }

    @Transactional
    public void delete(Integer id){
        Team team = teamRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("팀이 존재하지 않습니다. teamId = " + id));

        teamRepository.deleteById(id);
    }
}
