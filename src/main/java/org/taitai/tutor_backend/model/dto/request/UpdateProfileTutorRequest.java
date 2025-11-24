package org.taitai.tutor_backend.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateProfileTutorRequest {
    private String email;
    private String subject;
}
