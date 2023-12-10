package com.premierleague.reservation.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class testController {
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello Secured World!");
    }

    @GetMapping("/view")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to the Premier League Reservation System!");
    }
}
