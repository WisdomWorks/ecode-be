package com.example.codeE.security;

import com.example.codeE.model.user.User;
import com.example.codeE.service.jwtService.JwtService;
import com.example.codeE.service.user.UserImpl;
import com.example.codeE.service.user.UserService;
import com.mongodb.lang.NonNull;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.security.config.web.server.SecurityWebFiltersOrder.AUTHENTICATION;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(String.valueOf(AUTHENTICATION));
        final String userName;
        final String jwtToken;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);
        userName = jwtService.extractUserName(jwtToken);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            User userDetail = userService.getUserByUserName(userName);
            if (jwtService.isTokenInvalid(jwtToken, userDetail)) {
                UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(
                        userDetail, null, userDetail.getAuthorities());
                authenToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenToken);
            }
        }
    }
}
