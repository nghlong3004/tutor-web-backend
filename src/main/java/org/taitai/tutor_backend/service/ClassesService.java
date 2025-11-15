package org.taitai.tutor_backend.service;

import org.springframework.http.ResponseEntity;
import org.taitai.tutor_backend.request.ClassesRequest;


public interface ClassesService {
    ResponseEntity<String> hiringsTutor(ClassesRequest classesRequest);
}
