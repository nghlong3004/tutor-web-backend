package org.taitai.newfolder.service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.taitai.newfolder.model.dto.respone.RefreshTokenRespone;


public interface RefreshTokenService {
    RefreshTokenRespone creatAccessTokenByRefreshToken(HttpServletRequest token);

}
