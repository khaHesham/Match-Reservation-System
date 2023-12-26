package com.premierleague.reservation.api.repositories;

import com.premierleague.reservation.api.models.Match;
import com.premierleague.reservation.api.models.Tickets;
import com.premierleague.reservation.api.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {
    List<Tickets> findTicketsByMatchId(Long matchId);

    List<Tickets> findTicketsByUserAndMatch(User user, Match match);

    void deleteByMatchAndUser(Match matchById, User userById);

    void deleteAllInBatch(Iterable<Tickets> entities);
}