package com.premierleague.reservation.api.service;

import com.premierleague.reservation.api.dtos.MatchDTO;
import com.premierleague.reservation.api.models.Match;
import com.premierleague.reservation.api.models.Tickets;
import com.premierleague.reservation.api.repositories.MatchRepository;
import com.premierleague.reservation.api.repositories.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketsService {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private TicketsRepository ticketsRepository;


    // TODO: is that the right way to get the user Id ??
    public boolean reserveTicket(String username,int seatNumber, long matchId) {

        List<Tickets> tickets = matchRepository.findById(matchId).get().getTickets();

        for (Tickets ticket : tickets) {
            if (ticket.getSeatNumber() == seatNumber) {
                return false;   // already reserved
            }
        }

        // reserve ticket
        // we need some extra information to reserve a ticket (user, match, seatNumber)
        // we need to create a new ticket object
        Tickets ticket = Tickets.builder()
                .seatNo(seatNumber)
                .user(userService.getUserByUsername(username))
                .match(matchService.getMatchById(matchId))
                .build();

        ticketsRepository.save(ticket);
        return true;

    }

    public void deleteTicket(Long id) {
        if (!ticketsRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found with id: " + id);
        }

        ticketsRepository.deleteById(id);
    }
}
