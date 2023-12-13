package com.premierleague.reservation.api.service;

import com.premierleague.reservation.api.dtos.StadiumDTO;
import com.premierleague.reservation.api.mappers.StadiumMapper;
import com.premierleague.reservation.api.models.Stadium;
import com.premierleague.reservation.api.repositories.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StadiumService {
    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private StadiumMapper stadiumMapper;

    public StadiumDTO createStadium(StadiumDTO stadiumDTO) {
        Stadium stadium = stadiumRepository.save(stadiumMapper.toEntity(stadiumDTO));
        return stadiumMapper.toDTO(stadium);
    }

}
