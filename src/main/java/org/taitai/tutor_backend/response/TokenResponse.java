package org.taitai.tutor_backend.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private Long userId;

}
