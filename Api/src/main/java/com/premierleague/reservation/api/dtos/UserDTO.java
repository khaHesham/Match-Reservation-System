package com.premierleague.reservation.api.dtos;

import com.premierleague.reservation.api.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.premierleague.reservation.api.models.User}
 */


public class UserDTO {
    private Long id;

    private String username;

    private String firstName;
    private String lastName;
    private String password;
    private Role role;
}