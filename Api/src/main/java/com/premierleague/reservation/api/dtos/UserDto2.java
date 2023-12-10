package com.premierleague.reservation.api.dtos;

import com.premierleague.reservation.api.models.enums.Role;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.premierleague.reservation.api.models.User}
 */
@Value
public class UserDto2 implements Serializable {
    Long id;
    String username;
    String firstName;
    String lastName;
    String password;
    String email;
    Role role;
}