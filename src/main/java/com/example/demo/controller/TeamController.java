package com.example.demo.controller;

import com.example.demo.controller.dto.TeamCreateRequestDto;
import com.example.demo.controller.dto.TeamResponseDto;
import com.example.demo.service.TeamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TeamController {
    TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDto> team(@PathVariable Integer id){
        TeamResponseDto team = teamService.findById(id);
        return ResponseEntity.ok(team);
    }

    @GetMapping("")
    public ResponseEntity<List<TeamResponseDto>> teams(){
        List<TeamResponseDto> teams = teamService.findAll();
        return ResponseEntity.ok(teams);
    }

    @PostMapping("")
    public ResponseEntity<TeamResponseDto> create(@RequestBody TeamCreateRequestDto requestDto){
        TeamResponseDto team = teamService.save(requestDto);
        return ResponseEntity.ok(team);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        teamService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
