package com.example.product_catalog.mapper;

import com.example.product_catalog.entity.User;
import com.example.product_catalog.payload.dto.UserDTO;

public class UserMapper {
    public static UserDTO ToDTO(User user){
        return UserDTO.builder()
            .userName(user.getUsername())
            .email(user.getEmail())
            .password(user.getPassword())
            .roles(user.getRoles())
        .build();
    }
}
