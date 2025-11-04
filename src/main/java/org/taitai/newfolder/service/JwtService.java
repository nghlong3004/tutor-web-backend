package org.taitai.newfolder.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.taitai.newfolder.enumm.TokenType;


public interface JwtService {
    String generateAccessToken(UserDetails userDetails , TokenType tokenType);
    String generateRefreshToken(UserDetails userDetails , TokenType tokenType);
    String extractUsername(String token ,TokenType tokenType);
    boolean isValid(String token,TokenType tokenType, UserDetails userDetails);




    UserDetailsService userDetailsService();

}
