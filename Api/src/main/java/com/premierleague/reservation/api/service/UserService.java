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

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
    }

    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        return user.get();
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (!user.isPresent()) {
            throw new RuntimeException("User not found with username: " + username);
        }
        return user.get();
    }
}
