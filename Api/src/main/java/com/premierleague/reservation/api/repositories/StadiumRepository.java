package com.premierleague.reservation.api.repositories;

import com.premierleague.reservation.api.models.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long> {
Stadium findStadiumByName(String name);
}