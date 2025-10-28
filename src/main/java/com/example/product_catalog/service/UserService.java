package com.example.product_catalog.service;

import java.util.List;

import com.example.product_catalog.payload.dto.UserDTO;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long UserId) throws Exception;

    UserDTO updateUser(Long UserId,UserDTO userDTO) throws Exception;

    void deleteUser(Long UserId) throws Exception;

}
