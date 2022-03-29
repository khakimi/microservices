package com.example.auth.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
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
}