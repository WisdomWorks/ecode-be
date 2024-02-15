package com.example.codeE.service.jwtService;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtImpl implements JwtService {

    @Override
    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public String generateToken(Map<String, Object> extractClaims, User user) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Boolean isTokenInvalid(String token, User user) {
        final String userName = extractUserName(token);
        return (userName.equals(user.getUsername()));
    }

    public String genrateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }

    @Override
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(Constant.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
