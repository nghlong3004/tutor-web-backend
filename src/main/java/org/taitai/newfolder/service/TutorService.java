package org.taitai.newfolder.service;

import org.springframework.http.ResponseEntity;
import org.taitai.newfolder.model.dto.request.TutorSignUpRequest;

public interface TutorService {
    ResponseEntity<String> signUp(TutorSignUpRequest tutorRequest);
}
