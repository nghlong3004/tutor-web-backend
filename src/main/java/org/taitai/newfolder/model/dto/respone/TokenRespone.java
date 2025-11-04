package org.taitai.newfolder.model.dto.respone;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TokenRespone {
    private String accessToken;
    private String refreshToken;
    private Long userId;

}
