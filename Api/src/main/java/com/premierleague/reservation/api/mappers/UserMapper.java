package com.premierleague.reservation.api.mappers;

import com.premierleague.reservation.api.dtos.UserDTO;
import com.premierleague.reservation.api.models.User;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

    List<UserDTO> toDTOs(List<User> users);
}
