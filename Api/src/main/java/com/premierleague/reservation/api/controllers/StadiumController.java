package com.premierleague.reservation.api.controllers;

import com.premierleague.reservation.api.dtos.StadiumDTO;
import com.premierleague.reservation.api.exceptions.UnauthorizedException;
import com.premierleague.reservation.api.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stadium")
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;

    @PostMapping("/create")
    public ResponseEntity<StadiumDTO> createStadium(@RequestBody StadiumDTO stadiumDTO){
        try {
            return new ResponseEntity<>(stadiumService.createStadium(stadiumDTO), HttpStatus.CREATED);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }




}
