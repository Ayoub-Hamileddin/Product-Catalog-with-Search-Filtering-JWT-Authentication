package com.example.product_catalog.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.product_catalog.domain.ERole;
import com.example.product_catalog.entity.Role;
import com.example.product_catalog.repository.RoleRepository;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner { 
    private final RoleRepository roleRepository;


    /*
     * when the app is running it create the table of role but it is empty ,
     * so when we want to register a user we need to select the default role in DB but the table of role is empty,
     * so we need when the app is running to initialize and insert the role value into the tables.
     * so when we want to 
     */
    
    
    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {

            roleRepository.save(new Role(null,ERole.ROLE_USER));

        }
        if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {

            roleRepository.save(new Role(null,ERole.ROLE_ADMIN));

        }
    }

}
