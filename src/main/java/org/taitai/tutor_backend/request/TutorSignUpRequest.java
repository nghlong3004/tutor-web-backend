package org.taitai.tutor_backend.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TutorSignUpRequest {
    private String name;
    private String email;
    private String subject;
}
