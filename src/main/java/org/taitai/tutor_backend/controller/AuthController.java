package org.taitai.tutor_backend.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taitai.tutor_backend.request.RefreshTokenRequest;
import org.taitai.tutor_backend.request.UserLoginRequest;
import org.taitai.tutor_backend.request.UserSignUpRequest;
import org.taitai.tutor_backend.respone.RefreshTokenRespone;
import org.taitai.tutor_backend.respone.TokenRespone;
import org.taitai.tutor_backend.service.RefreshTokenService;
import org.taitai.tutor_backend.service.AuthService;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final AuthService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping
    @RequestMapping("/signup")
    public TokenRespone signup(@RequestBody UserSignUpRequest signUpRequest) {
        log.info("Signup controller called for username: {}", signUpRequest.getUsername());
        return userService.signup(signUpRequest);
    }
    @PostMapping
    @RequestMapping("/login")
    public TokenRespone login(@RequestBody UserLoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
    // đang hoàn thành ( chưa xong logout )
    @PostMapping
    @RequestMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest refreshToken) {
        return userService.logout(refreshToken);
    }
    @PostMapping("/refresh")
    public RefreshTokenRespone refresh(HttpServletRequest token) {
        log.info(">>> [Controller] Refresh endpoint called with: " + token.getHeader("Authorization"));
        return refreshTokenService.creatAccessTokenByRefreshToken(token) ;
    }


}
