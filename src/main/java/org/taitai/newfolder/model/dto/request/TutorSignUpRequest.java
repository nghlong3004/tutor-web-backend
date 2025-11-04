package org.taitai.newfolder.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TutorSignUpRequest {
    private String name;
    private String email;
    private String subject;
}
