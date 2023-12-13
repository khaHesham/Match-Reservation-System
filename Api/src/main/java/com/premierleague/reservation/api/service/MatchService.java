package com.premierleague.reservation.api.service;

import com.premierleague.reservation.api.dtos.MatchDTO;
import com.premierleague.reservation.api.mappers.MatchMapper;
import com.premierleague.reservation.api.models.Match;
import com.premierleague.reservation.api.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchMapper matchMapper;

    public List<MatchDTO> getAllMatches() {
        Optional<List<Match>> matches = Optional.ofNullable(matchRepository.findAll());
        if (matches.isEmpty()) {
            throw new RuntimeException("No matches found");
        }
        else {
            return matchMapper.toDTOs(matches.get());
        }
    }

    public MatchDTO createMatch(MatchDTO matchDTO) {
        Match match = matchRepository.save(matchMapper.toEntity(matchDTO));
        return matchMapper.toDTO(match);
    }

    public MatchDTO updateMatch(MatchDTO matchDTO) {
        if (!matchRepository.existsById(matchDTO.getId())) {
            throw new RuntimeException("Match not found with id: " + matchDTO.getId());
        }

        Match match = matchRepository.save(matchMapper.toEntity(matchDTO));
        return matchMapper.toDTO(match);
    }
}
