package com.premierleague.reservation.api.service;

import com.premierleague.reservation.api.dtos.MatchDTO;
import com.premierleague.reservation.api.models.Match;
import com.premierleague.reservation.api.models.Tickets;
import com.premierleague.reservation.api.repositories.MatchRepository;
import com.premierleague.reservation.api.repositories.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    @Autowired
    private AuthenticationService authenticationService;


    // TODO: is that the right way to get the user Id ??
    public boolean reserveTicket(String username,int seatNumber, long matchId) {

        List<Tickets> tickets = matchRepository.findById(matchId).get().getTickets();

        for (Tickets ticket : tickets) {
            if (ticket.getSeatNumber() == seatNumber ) {
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
                .time((new java.util.Date()).toString())
                .build();

        ticketsRepository.save(ticket);
        return true;

    }

    public void deleteTicket(Long MatchId) {
        if (!matchRepository.existsById(MatchId)) {
            throw new RuntimeException("Match not found with id: " + MatchId);
        }

        // delete ticket reserved by me
        // we need to check if the ticket is reserved by me

        String username = authenticationService.getUsername();
        Long userId = userService.getUserByUsername(username).getId();

        List<Tickets> tickets = ticketsRepository.findTicketsByUserAndMatch(userService.getUserById(userId), matchService.getMatchById(MatchId));

        if (tickets.isEmpty()) {
            throw new RuntimeException("You dont have a ticket for this match");
        }

        for (Tickets ticket : tickets) {
            if (!Objects.equals(ticket.getUser().getId(), userId)) {
                throw new RuntimeException("You are not authorized to perform this action");
            }
        }

        ticketsRepository.deleteAllInBatch(tickets);

//        ticketsRepository.deleteByMatchAndUser(matchService.getMatchById(MatchId), userService.getUserById(userId));
    }
}
