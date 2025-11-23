package org.taitai.tutor_backend.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClassesResponse {
    private String username;
    private String description;
}
