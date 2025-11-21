package org.taitai.tutor_backend.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.taitai.tutor_backend.type.ApplyStatus;

@Getter
@Setter
@Builder
public class ApplyTutorResponse {
    private Long tutorId;
    private Long classId;
    private ApplyStatus status;
}
