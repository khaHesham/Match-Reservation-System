package com.premierleague.reservation.api.service;

import com.premierleague.reservation.api.controllers.auth.AuthenticationRequest;
import com.premierleague.reservation.api.controllers.auth.AuthenticationResponse;
import com.premierleague.reservation.api.controllers.auth.RegisterRequest;
import com.premierleague.reservation.api.models.enums.Role;
import com.premierleague.reservation.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import com.premierleague.reservation.api.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private String jwtToken;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getUsername())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole() == null ? Role.USER : Role.valueOf(request.getRole()))
            .build();
        userRepository.save(user);

        jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public String getUsername(){
        return jwtService.extractUsername(jwtToken);
    }

    public String getRole(){
        return userRepository.findUserByUsername(getUsername()).get().getRole().toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
    )
    );
    var user = userRepository.findUserByUsername(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
