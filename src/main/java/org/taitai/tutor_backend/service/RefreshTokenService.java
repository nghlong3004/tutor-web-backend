package org.taitai.tutor_backend.service;
import jakarta.servlet.http.HttpServletRequest;
import org.taitai.tutor_backend.response.RefreshTokenResponse;


public interface RefreshTokenService {
    RefreshTokenResponse creatAccessTokenByRefreshToken(HttpServletRequest token);

}
