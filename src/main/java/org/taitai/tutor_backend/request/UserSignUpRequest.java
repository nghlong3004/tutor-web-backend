package org.taitai.tutor_backend.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserSignUpRequest {
    private String username;
    private String password;
}
