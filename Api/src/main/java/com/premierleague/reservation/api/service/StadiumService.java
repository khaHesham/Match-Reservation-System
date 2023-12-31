package com.premierleague.reservation.api.service;

import com.premierleague.reservation.api.dtos.StadiumDTO;
import com.premierleague.reservation.api.exceptions.UnauthorizedException;
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
    private AuthenticationService authenticationService;

    @Autowired
    private StadiumMapper stadiumMapper;

    public Stadium getStadiumById(Long stadId) {
        return stadiumRepository.findById(stadId).get();
    }

    public StadiumDTO createStadium(StadiumDTO stadiumDTO) {
        Stadium stadium = stadiumRepository.save(stadiumMapper.toEntity(stadiumDTO));

        if(!authenticationService.getRole().equals("EFA_MANAGER")){
            throw new UnauthorizedException("You are not authorized to perform this action");
        }

        return stadiumMapper.toDTO(stadium);
    }

}
