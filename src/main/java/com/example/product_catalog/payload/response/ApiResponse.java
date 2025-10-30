package com.example.product_catalog.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder    
public class ApiResponse {
    private  String message;
}
