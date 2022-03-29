package com.example.userregistry.security;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class JwtUtil {
    public boolean validateToken(final String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
        } catch (SignatureException |
                 MalformedJwtException |
                 ExpiredJwtException |
                 UnsupportedJwtException |
                 IllegalArgumentException ex) {
            log.error(ex.getMessage());
            throw ex;
        }
        return true;
    }

    public String getUserPhoneNumberFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("phoneNumber");
    }
}