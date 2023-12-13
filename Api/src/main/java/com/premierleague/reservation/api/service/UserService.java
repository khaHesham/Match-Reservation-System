package com.premierleague.reservation.api.service;

import com.premierleague.reservation.api.dtos.UserDTO;
import com.premierleague.reservation.api.mappers.UserMapper;
import com.premierleague.reservation.api.models.User;
import com.premierleague.reservation.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  UserMapper userMapper;

    public UserDTO userProfile(String username) {
        Optional<User> user = Optional.ofNullable(userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found")));

        return userMapper.toDTO(user.get());
    }


    public UserDTO updateUser(UserDTO userDTO) {
        if (!userRepository.existsById(userDTO.getId())) {
            throw new RuntimeException("User not found with id: " + userDTO.getId());
        }

        User user = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDTO(user);
    }
}
