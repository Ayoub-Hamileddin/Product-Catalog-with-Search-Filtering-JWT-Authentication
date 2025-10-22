package com.example.product_catalog.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,@NotNull HttpServletResponse response,@NotNull FilterChain filterChain) throws IOException, ServletException{
        final String authHeader=request.getHeader("Authorization");
        final  String jwt;
        final  String  userEmail;

        if (authHeader==null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt=authHeader.substring(7);
        userEmail= jwtService.extractUserName(jwt);

        if (userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null  ) {

            UserDetails userDetails=userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken auth=
                            new UsernamePasswordAuthenticationToken(userEmail,null, userDetails.getAuthorities());
              
            auth.setDetails(
                new WebAuthenticationDetailsSource() .buildDetails(request)
            );       
            

                
            SecurityContextHolder.getContext().setAuthentication(auth);
            
            

            }

        }
        filterChain.doFilter(request, response);

    }

}
