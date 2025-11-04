package org.taitai.newfolder.service;

import org.springframework.http.ResponseEntity;
import org.taitai.newfolder.model.dto.request.ClassesRequest;
import org.taitai.newfolder.model.dto.request.TutorSignUpRequest;

public interface ClassesService {
    ResponseEntity<String> hiringsTutor(ClassesRequest classesRequest);
}
