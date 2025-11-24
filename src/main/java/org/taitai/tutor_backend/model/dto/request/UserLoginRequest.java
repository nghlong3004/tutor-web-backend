package org.taitai.tutor_backend.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class UserLoginRequest implements Serializable {
    private String username;
    private String password;
}
