package org.taitai.tutor_backend.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.taitai.tutor_backend.model.dto.request.RefreshTokenRequest;
import org.taitai.tutor_backend.model.dto.request.UserLoginRequest;
import org.taitai.tutor_backend.model.dto.request.UserSignUpRequest;
import org.taitai.tutor_backend.model.dto.response.TokenResponse;


public interface AuthService {

    TokenResponse login(UserLoginRequest loginRequest);
    TokenResponse signup(UserSignUpRequest signUpRequest);
    ResponseEntity<String> logout(RefreshTokenRequest refreshToken);


    ResponseEntity<String> logout(HttpServletRequest token);
}
