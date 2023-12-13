package com.premierleague.reservation.api.dtos;

import com.premierleague.reservation.api.models.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.premierleague.reservation.api.models.User}
 */
@Setter
@Getter
public class UserDTO implements Serializable {

    @Setter
    private Long id;

    @Setter
    private String username;

    @Setter
    private String firstName;

    @Setter
    private String lastName;

    @Setter
    private String email;

    @Setter
    private String city;

    @Setter
    private String birthDate;

    @Setter
    private Role role;

    @Setter
    private String Address;
    
}