package com.example.product_catalog.service;

import com.example.product_catalog.payload.dto.LoginRequest;
import com.example.product_catalog.payload.dto.RegisterRequest;
import com.example.product_catalog.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request) throws Exception;
    
    AuthResponse register(RegisterRequest request) throws Exception;

}
