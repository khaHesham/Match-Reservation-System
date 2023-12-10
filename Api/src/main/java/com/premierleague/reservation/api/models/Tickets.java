package com.premierleague.reservation.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String date;
    private String time;

    private String status;

    @Column(name = "seat_no")
    private int seatNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_match")
    private Match match;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user")
    private User user;

}
