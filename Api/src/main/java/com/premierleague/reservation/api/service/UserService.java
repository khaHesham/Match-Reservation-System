package com.premierleague.reservation.api.service;

import com.premierleague.reservation.api.dtos.UserDTO;
import com.premierleague.reservation.api.mappers.UserMapper;
import com.premierleague.reservation.api.models.User;
import com.premierleague.reservation.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private  UserMapper userMapper;

    public UserDTO userProfile(String username) {
        var user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        userDTO.setCity(user.getCity());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setAddress(user.getAddress());
        //System.out.println(userMapper.toDTO(user));

        return userDTO;

    }


}
