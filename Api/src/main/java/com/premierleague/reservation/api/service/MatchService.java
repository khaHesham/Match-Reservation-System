package com.premierleague.reservation.api.service;

import com.premierleague.reservation.api.dtos.MatchDTO;
import com.premierleague.reservation.api.exceptions.UnauthorizedException;
import com.premierleague.reservation.api.mappers.MatchMapper;
import com.premierleague.reservation.api.models.Match;
import com.premierleague.reservation.api.models.Stadium;
import com.premierleague.reservation.api.models.Tickets;
import com.premierleague.reservation.api.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private AuthenticationService authenticationService;


    public List<MatchDTO> getAllMatches() {
        Optional<List<Match>> matches = Optional.ofNullable(matchRepository.findAll());
        if (matches.isEmpty()) {
            throw new RuntimeException("No matches found");
        }
        else {
            return matchMapper.toDTOs(matches.get());
        }
    }

    public MatchDTO createMatch(MatchDTO matchDTO, Long stadId) {
       // check if iam manager
        if(!authenticationService.getRole().equals("EFA_MANAGER")){
            throw new UnauthorizedException("You are not authorized to perform this action");
        }

        Stadium stadium = stadiumService.getStadiumById(stadId);

        Match match = Match.builder()
                .date(matchDTO.getDate())
                .homeTeam(matchDTO.getHomeTeam())
                .awayTeam(matchDTO.getAwayTeam())
                .stadium(stadium)
                .referee(matchDTO.getReferee())
                .linesman1(matchDTO.getLinesman1())
                .linesman2(matchDTO.getLinesman2())
                .time((new Date()).toString())
                .build();

        matchRepository.save(match);
        return matchMapper.toDTO(match);
    }

    public MatchDTO updateMatch(MatchDTO matchDTO) {
        if (!matchRepository.existsById(matchDTO.getId())) {
            throw new RuntimeException("Match not found with id: " + matchDTO.getId());
        }

        if(!authenticationService.getRole().equals("EFA_MANAGER")){
            throw new UnauthorizedException("You are not authorized to perform this action");
        }

        Match match = matchRepository.save(matchMapper.toEntity(matchDTO));
        return matchMapper.toDTO(match);
    }

    public MatchDTO viewMatch(Long id) {
        Optional<Match> match = matchRepository.findById(id);
        if (match.isEmpty()) {
            throw new RuntimeException("Match not found with id: " + id);
        }
        else {
            return matchMapper.toDTO(match.get());
        }
    }

    public Match getMatchById(long matchId) {
        Optional<Match> match = matchRepository.findById(matchId);
        if (!match.isPresent()) {
            throw new RuntimeException("Match not found with id: " + matchId);
        }
        return match.get();
    }

    public List<Tickets> viewReservedMatches(Long id) {
        List<Tickets> tickets = matchRepository.findById(id).get().getTickets();

        if (tickets.isEmpty())
            throw new RuntimeException("No tickets found for this match");

        return tickets;
    }
}
