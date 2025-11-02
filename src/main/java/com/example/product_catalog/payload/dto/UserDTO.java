package com.example.product_catalog.payload.dto;

import java.util.List;

import com.example.product_catalog.entity.Role;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDTO {

    private String Name;

    private String email;

    private String password;

    private List<Role> roles;
}
