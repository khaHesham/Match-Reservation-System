package com.premierleague.reservation.api.controllers;

import com.premierleague.reservation.api.dtos.MatchDTO;
import com.premierleague.reservation.api.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomePageController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello Secured World!");
    }

    @GetMapping("/view")
    public ResponseEntity<List<MatchDTO>> welcome() {
        try {
            return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
