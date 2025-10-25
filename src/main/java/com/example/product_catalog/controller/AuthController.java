package com.example.product_catalog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_catalog.payload.dto.LoginRequest;
import com.example.product_catalog.payload.dto.RegisterRequest;
import com.example.product_catalog.payload.response.AuthResponse;
import com.example.product_catalog.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
        private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) throws Exception{
        return authService.login(loginRequest);
    }


    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest) throws Exception{
        return authService.register(registerRequest);
    }

}
