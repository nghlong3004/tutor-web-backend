package org.taitai.tutor_backend.service;

import org.springframework.http.ResponseEntity;
import org.taitai.tutor_backend.request.TutorSignUpRequest;


public interface TutorService {
    ResponseEntity<String> signUp(TutorSignUpRequest tutorRequest);
}
