package com.premierleague.reservation.api.dtos;

import com.premierleague.reservation.api.models.enums.Team;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.premierleague.reservation.api.models.Match}
 */
@Setter
@Getter
public class MatchDTO implements Serializable {
    @Setter
    Long id;
    @Setter
    Team homeTeam;
    @Setter
    Team awayTeam;
    @Setter
    String date;
    @Setter
    String time;
    @Setter
    String referee;
    @Setter
    String linesman1;
    @Setter
    String linesman2;
}