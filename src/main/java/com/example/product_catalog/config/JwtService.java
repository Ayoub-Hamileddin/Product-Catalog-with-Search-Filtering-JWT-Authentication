package com.example.product_catalog.config;

import java.security.Key;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private final static String SECRET_KEY="wUEIsKbbjn+3Wgu6TzDOk5MZ3BD4TbXlzdXBlclNlY3JldGZvcnRoaXNhcHBoaGho";



    public Claims extractAllCalims(String jwt){
            return  Jwts
                    .parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
                
                    
    }

    public <T> T extractClaim(String jwt,Function<Claims,T>claimsResolver){
       final  Claims Claims=extractAllCalims(jwt);
        return claimsResolver.apply(Claims);
    }

    public String extractUserName(String jwt){
        return extractClaim(jwt, Claims::getSubject);
    }


    private SecretKey getSignKey(){
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
