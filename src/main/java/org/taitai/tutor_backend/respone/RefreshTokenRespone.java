package org.taitai.tutor_backend.respone;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RefreshTokenRespone {
    private String accessToken;
}
