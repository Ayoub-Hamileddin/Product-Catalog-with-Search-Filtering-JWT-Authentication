package com.example.product_catalog.payload.response;

import com.example.product_catalog.payload.dto.UserDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String jwt;

    private String message;

    private UserDTO UserDto;

    
}
