package com.fyaora.security.oauth_service.config;

import com.fyaora.security.oauth_service.service.TokenStoreService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import com.fyaora.security.oauth_service.util.JwtTokenUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final TokenStoreService tokenStoreService;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, TokenStoreService tokenStoreService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenStoreService = tokenStoreService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract the JWT token from the request header
        String token = jwtTokenUtil.getTokenFromRequest(request);

        if (token != null && tokenStoreService.validateToken(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, null
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
