package com.example.product_catalog.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

   @Column(unique = true,nullable = false) 
   @Email
    private String email;

    private String password;

    @ManyToMany
    private List<Role> roles;





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return roles.stream()
                  .map(role->new SimpleGrantedAuthority(role.getName().name()))
                  .toList();
    }

    @Override
    public String getUsername() {
       return email;
    }

}
