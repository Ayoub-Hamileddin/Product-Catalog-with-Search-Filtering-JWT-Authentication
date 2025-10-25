package com.example.product_catalog.service.Impl;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.product_catalog.config.JwtService;
import com.example.product_catalog.domain.ERole;
import com.example.product_catalog.entity.Role;
import com.example.product_catalog.entity.User;
import com.example.product_catalog.mapper.UserMapper;
import com.example.product_catalog.payload.dto.LoginRequest;
import com.example.product_catalog.payload.dto.RegisterRequest;
import com.example.product_catalog.payload.response.AuthResponse;
import com.example.product_catalog.repository.RoleRepository;
import com.example.product_catalog.repository.UserRepository;
import com.example.product_catalog.service.AuthService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) throws Exception {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
            ()->new Exception("User not Found")
        );

        String jwt = jwtService.generateToken(null, user);
        return AuthResponse.builder()
                    .jwt(jwt)
                    .message("Login successfuly")
                    .UserDto(UserMapper.ToDTO(user))
            .build();
    }


    
    @Override
    public AuthResponse register(RegisterRequest request) throws Exception {
        Role defaultRole=roleRepository.findByName(ERole.ROLE_USER).orElseThrow(
            ()->new Exception("Role Not Found")
            );

        User user=User.builder()
                    .userName(request.getUserName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(List.of(defaultRole))
                .build();

        User usersaved =userRepository.save(user);     
        String jwt=jwtService.generateToken(null, user);
        return AuthResponse.builder()
                .jwt(jwt)
                .message("Registered successfuly")
                .UserDto(UserMapper.ToDTO(usersaved))
        .build();

    }

}
