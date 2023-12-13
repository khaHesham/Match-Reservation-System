package com.premierleague.reservation.api.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.premierleague.reservation.api.models.Stadium}
 */
@Setter
@Getter
public class StadiumDTO implements Serializable {
    @Setter
    long id;
    @Setter
    String name;
    @Setter
    String city;
    @Setter
    int capacity;
}