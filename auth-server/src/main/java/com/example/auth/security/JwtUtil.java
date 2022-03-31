package com.example.auth.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtUtil {

    public String generateToken(String phoneNumber) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("phoneNumber", phoneNumber);
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + SecurityConstants.EXPIRATION_TIME;
        return Jwts.builder()
                .setSubject(phoneNumber)
                .setClaims(claimsMap)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(expMillis))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

    public boolean validateToken(final String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
        } catch (SignatureException |
                 MalformedJwtException |
                 ExpiredJwtException |
                 UnsupportedJwtException |
                 IllegalArgumentException ex) {
            log.error(ex.getMessage());
            return false;
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