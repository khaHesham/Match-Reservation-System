package com.premierleague.reservation.api.repositories;

import com.premierleague.reservation.api.models.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketsRepository extends JpaRepository<Tickets, Long> {
    List<Tickets> findTicketsByMatchId(Long matchId);
}