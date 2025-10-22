package com.example.product_catalog.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    
    private String email;

    private String password;
}
