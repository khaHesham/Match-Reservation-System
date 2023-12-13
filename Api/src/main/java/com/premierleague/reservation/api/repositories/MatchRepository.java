package com.premierleague.reservation.api.repositories;

import com.premierleague.reservation.api.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAll(); // return all matches

}