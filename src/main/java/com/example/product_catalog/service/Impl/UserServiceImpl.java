package com.example.product_catalog.service.Impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.product_catalog.entity.User;
import com.example.product_catalog.mapper.UserMapper;
import com.example.product_catalog.payload.dto.UserDTO;
import com.example.product_catalog.repository.UserRepository;
import com.example.product_catalog.service.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    
    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users=userRepository.findAll();

        return users.stream()
                            .map(UserMapper::ToDTO)
                            .toList();

    }



    @Override
    public UserDTO getUserById(Long UserId) throws Exception {
       User user =userRepository.findById(UserId).orElseThrow(
             ()->new Exception("User Not Found")
       );
       return UserMapper.ToDTO(user);
    }



    @Override
    public UserDTO updateUser(Long UserId, UserDTO userDTO) throws Exception {
        User user =userRepository.findById(UserId).orElseThrow(
             ()->new Exception("User Not Found")
       );

       user.setEmail(userDTO.getEmail());
       user.setUserName(userDTO.getUserName());
       user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
       user.setRoles(userDTO.getRoles());

       User savedUser= userRepository.save(user);

        return UserMapper.ToDTO(savedUser);
    }



    @Override
    public void deleteUser(Long UserId) throws Exception {

        User user =userRepository.findById(UserId).orElseThrow(
             ()->new Exception("User Not Found")
       );
       
        user.getRoles().clear();
        userRepository.save(user);

       userRepository.delete(user);
    }

}
