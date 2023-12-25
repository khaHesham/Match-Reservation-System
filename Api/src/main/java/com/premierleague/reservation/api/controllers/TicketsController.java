package com.premierleague.reservation.api.controllers;

import com.premierleague.reservation.api.repositories.UserRepository;
import com.premierleague.reservation.api.service.AuthenticationService;
import com.premierleague.reservation.api.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketsController {

    @Autowired
    private TicketsService tecketsService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/reserve/{matchId}/{seatN}")
    public boolean createTicket(@PathVariable("seatN") int seatNumber, @PathVariable("matchId") int matchId){

        return tecketsService.reserveTicket(authenticationService.getUsername(), seatNumber, matchId);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable("id") Long id) {
        try {
            // need to check if the user performing this action is an admin
            // else throw unauthorized exception
            tecketsService.deleteTicket(id);
            return ResponseEntity.ok("Ticket deleted successfully");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
