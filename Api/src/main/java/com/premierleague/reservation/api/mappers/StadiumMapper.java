package com.premierleague.reservation.api.mappers;

import com.premierleague.reservation.api.dtos.StadiumDTO;
import com.premierleague.reservation.api.models.Stadium;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StadiumMapper {
    Stadium toEntity(StadiumDTO stadiumDto);

    StadiumDTO toDTO(Stadium stadium);

    List<StadiumDTO> toDTOs(List<Stadium> stadiums);


}