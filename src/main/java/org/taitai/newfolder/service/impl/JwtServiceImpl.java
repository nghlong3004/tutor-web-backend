package org.taitai.newfolder.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.taitai.newfolder.repository.UserRepo;
import org.taitai.newfolder.service.JwtService;
import org.taitai.newfolder.enumm.TokenType;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.taitai.newfolder.enumm.TokenType.ACCESS_TOKEN;
import static org.taitai.newfolder.enumm.TokenType.REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.refreshKey}")
    private String refreshKey;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.expirationDate}")
    private long expirationDate;

    private final UserRepo userRepo;


    @Override
    public String generateAccessToken(UserDetails userDetails , TokenType tokenType) {
        return buildToken(new HashMap<>(), userDetails , expiration , ACCESS_TOKEN) ;
    }
    @Override
    public String generateRefreshToken(UserDetails userDetails , TokenType tokenType ) {
        return buildToken(new HashMap<>(), userDetails , expirationDate , REFRESH_TOKEN) ;
    }
    private String buildToken(Map<String, Object> claims , UserDetails userDetails , long expirationMillis , TokenType tokenType) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getKey(tokenType), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey(TokenType tokenType) {
        byte[] keyBytes;
            if(ACCESS_TOKEN.equals(tokenType)) {
                keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
            } else {
                keyBytes = refreshKey.getBytes(StandardCharsets.UTF_8);
            }
            return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String extractUsername(String token , TokenType tokenType) {
        return extractClaim(token, tokenType, Claims::getSubject);
    }
    private <T> T extractClaim(String token, TokenType tokenType, Function<Claims, T> claimsResolver) {
        final Claims claims = exctraAllClaims(token , tokenType);
        return claimsResolver.apply(claims);
    }
    private Claims exctraAllClaims(String token , TokenType tokenType) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey(tokenType))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Date extractExpiration(String token , TokenType tokenType) {
        return extractClaim(token, tokenType , Claims::getExpiration);
    }
    @Override
    public boolean isValid(String token,TokenType tokenType, UserDetails userDetails) {
        final String username = extractUsername(token , tokenType);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token , tokenType);
    }
    public  boolean isTokenExpired(String token , TokenType tokenType) {
        return extractExpiration(token, tokenType).before(new Date());
    }
    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }


}
