package com.premierleague.reservation.api.controllers;

import com.premierleague.reservation.api.dtos.MatchDTO;
import com.premierleague.reservation.api.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/match")
public class MatchController {
@Autowired
private MatchService matchService;
    @PostMapping("/create")
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchDTO match) {
        return new ResponseEntity<>(matchService.createMatch(match), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<MatchDTO> updateMatch(@RequestBody MatchDTO match) {
        return new ResponseEntity<>(matchService.updateMatch(match), HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<MatchDTO> viewMatch(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(matchService.viewMatch(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
