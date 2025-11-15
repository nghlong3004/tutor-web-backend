package org.taitai.tutor_backend.service;
import jakarta.servlet.http.HttpServletRequest;
import org.taitai.tutor_backend.respone.RefreshTokenRespone;


public interface RefreshTokenService {
    RefreshTokenRespone creatAccessTokenByRefreshToken(HttpServletRequest token);

}
