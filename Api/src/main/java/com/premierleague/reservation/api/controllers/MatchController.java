package com.premierleague.reservation.api.controllers;

import com.premierleague.reservation.api.dtos.MatchDTO;
import com.premierleague.reservation.api.exceptions.UnauthorizedException;
import com.premierleague.reservation.api.models.Tickets;
import com.premierleague.reservation.api.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/match")
public class MatchController {
@Autowired
private MatchService matchService;
    @PostMapping("/create/{stadId}")
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchDTO match, @PathVariable("stadId") Long stadId) {
        try {
            return new ResponseEntity<>(matchService.createMatch(match,stadId), HttpStatus.CREATED);
        }catch (UnauthorizedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

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

    @GetMapping("/reserved/{id}")
    public ResponseEntity<List<Tickets>> viewReservedMatches(@PathVariable("id") Long id) {
        try {
            List<Tickets> tickets = matchService.viewReservedMatches(id);
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
