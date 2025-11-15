package org.taitai.tutor_backend.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.taitai.tutor_backend.request.RefreshTokenRequest;
import org.taitai.tutor_backend.request.UserLoginRequest;
import org.taitai.tutor_backend.request.UserSignUpRequest;
import org.taitai.tutor_backend.respone.TokenRespone;


public interface AuthService {

    TokenRespone login(UserLoginRequest loginRequest);
    TokenRespone signup(UserSignUpRequest signUpRequest);
    ResponseEntity<String> logout(RefreshTokenRequest refreshToken);


    ResponseEntity<String> logout(HttpServletRequest token);
}
