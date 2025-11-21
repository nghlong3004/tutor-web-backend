package org.taitai.tutor_backend.service;

import org.taitai.tutor_backend.model.Classes;
import org.taitai.tutor_backend.request.ClassesRequest;
import org.taitai.tutor_backend.response.ApplyTutorResponse;
import org.taitai.tutor_backend.response.ClassesResponse;

import java.util.List;


public interface ClassesService {
    ClassesResponse hiringsTutor(ClassesRequest classesRequest);

    List<Classes> getClasses();

    ApplyTutorResponse applyClass(Long classId);
}
