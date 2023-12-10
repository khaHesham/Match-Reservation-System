package com.premierleague.reservation.api.models;

import com.premierleague.reservation.api.models.enums.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Match{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Team homeTeam;

    @Enumerated(EnumType.STRING)
    private Team awayTeam;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "referee")
    private String referee;

    @Column(name = "linesman1")
    private String linesman1;

    @Column(name = "linesman2")
    private String linesman2;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<Tickets> tickets = new ArrayList<Tickets>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_stadium")
    private Stadium stadium;

}
