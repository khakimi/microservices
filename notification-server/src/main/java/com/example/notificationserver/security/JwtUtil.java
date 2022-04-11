package com.example.notificationserver.security;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public String getUserPhoneNumberFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("phoneNumber");
    }
}