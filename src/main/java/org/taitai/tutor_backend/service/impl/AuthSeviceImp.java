package org.taitai.tutor_backend.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.taitai.tutor_backend.model.RefreshToken;
import org.taitai.tutor_backend.model.User;
import org.taitai.tutor_backend.repository.RefreshTokenRepo;
import org.taitai.tutor_backend.repository.UserRepo;
import org.taitai.tutor_backend.request.RefreshTokenRequest;
import org.taitai.tutor_backend.request.UserLoginRequest;
import org.taitai.tutor_backend.request.UserSignUpRequest;
import org.taitai.tutor_backend.respone.TokenRespone;
import org.taitai.tutor_backend.service.JwtService;
import org.taitai.tutor_backend.service.AuthService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.taitai.tutor_backend.type.TokenType.ACCESS_TOKEN;
import static org.taitai.tutor_backend.type.TokenType.REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthSeviceImp implements AuthService {
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepo refreshTokenRepo;


    @Override
    public TokenRespone login(UserLoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        var user = userRepo.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Invalid email or password")) ;
        String accessToken = jwtService.generateAccessToken(user, ACCESS_TOKEN);
        String refreshToken = jwtService.generateRefreshToken(user, REFRESH_TOKEN);

        TokenRespone tokenRespone =  TokenRespone.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();
        RefreshToken r = new RefreshToken();
        r.setToken(refreshToken);
        r.setUser(user);
        r.setRevoked(false);
        r.setExpiryDate(Instant.now().plus(30, ChronoUnit.DAYS));
        log.info("[53] check round {}", r.getUser().getId());
        refreshTokenRepo.save(r);
        return tokenRespone;
    }

    @Override
    public TokenRespone signup(UserSignUpRequest signUpRequest) {
        if (userRepo.findByUsername(signUpRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepo.save(user);
        log.info("getUsername {}", signUpRequest.getUsername());
        String accessToken = jwtService.generateAccessToken(user, ACCESS_TOKEN);
        String refreshToken = jwtService.generateRefreshToken(user, REFRESH_TOKEN);
        TokenRespone tokenRespone = TokenRespone.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();
        RefreshToken r = new RefreshToken();
        r.setToken(refreshToken);
        r.setUser(user);
        r.setRevoked(false);
        r.setExpiryDate(Instant.now().plus(30, ChronoUnit.DAYS));
        refreshTokenRepo.save(r);
        return tokenRespone;
    }

    @Override
    public ResponseEntity<String> logout(RefreshTokenRequest refreshToken) {
        return null;
    }

    @Override
    public ResponseEntity<String> logout(HttpServletRequest token) {
        String refreshToken = token.getHeader("Authorization").substring(7);
        if (refreshToken.isBlank()) {
            throw new RuntimeException("Refresh token has no Authorization header");
        }
        log.info(">>> [Service] Refresh endpoint called with: {}", refreshToken);
        RefreshToken ref = refreshTokenRepo.findByToken(refreshToken).orElseThrow(()->new RuntimeException("token not found"));
        refreshTokenRepo.delete(ref);
        return ResponseEntity.ok().build();
    }

}
