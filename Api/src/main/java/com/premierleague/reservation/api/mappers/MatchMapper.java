package com.premierleague.reservation.api.mappers;

import com.premierleague.reservation.api.dtos.MatchDTO;
import com.premierleague.reservation.api.models.Match;
import org.mapstruct.Mapper;


import java.util.Collection;
import java.util.List;
import java.util.Set;
@Mapper(componentModel = "spring")
public interface MatchMapper {

    Match toEntity(MatchDTO matchDTO);

    MatchDTO toDTO(Match match);

    List<MatchDTO> toDTOs(List<Match> match);


}
