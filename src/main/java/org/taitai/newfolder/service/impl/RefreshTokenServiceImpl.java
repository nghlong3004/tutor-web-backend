package org.taitai.newfolder.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.taitai.newfolder.model.dto.respone.RefreshTokenRespone;
import org.taitai.newfolder.model.entity.RefreshToken;
import org.taitai.newfolder.repository.RefreshTokenRepo;
import org.taitai.newfolder.service.JwtService;
import org.taitai.newfolder.service.RefreshTokenService;
import org.taitai.newfolder.enumm.TokenType;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepo refreshTokenRepo;
    private final JwtService jwtService;
    @Override
    public RefreshTokenRespone creatAccessTokenByRefreshToken(HttpServletRequest token) {

        String refreshToken = token.getHeader("Authorization").substring(7);
        if (refreshToken.isBlank()) {
            throw new RuntimeException("Refresh token has no Authorization header");
        }
        log.info(">>> [Service] Refresh endpoint called with: " + refreshToken);
        RefreshToken ref = refreshTokenRepo.findByToken(refreshToken).orElseThrow(()->new RuntimeException("token not found"));
        log.info(">>> [Service] check {} ", refreshToken);
        if (ref.isRevoked()) {
            refreshTokenRepo.delete(ref);
            throw new RuntimeException("Token is revoked");
        }
        if (ref.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepo.delete(ref);
            throw new RuntimeException("Token is expired");
        }
        String accessToken = jwtService.generateAccessToken(ref.getUser() , TokenType.ACCESS_TOKEN);
        log.info(">>> [Service] Access Token: " + accessToken);
        return RefreshTokenRespone.builder()
                .accessToken(accessToken)
                .build();
    }
}
