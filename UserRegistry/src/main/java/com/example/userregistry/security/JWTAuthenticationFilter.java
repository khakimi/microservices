package com.example.userregistry.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    public static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = httpServletRequest.getHeader("Authorization");
        if(!jwtUtil.validateToken(token)){
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

}