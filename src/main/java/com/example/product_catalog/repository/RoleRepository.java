package com.example.product_catalog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product_catalog.domain.ERole;
import com.example.product_catalog.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(ERole name);
    
}
