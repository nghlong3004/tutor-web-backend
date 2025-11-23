package org.taitai.tutor_backend.service;

import org.taitai.tutor_backend.model.entity.Classes;
import org.taitai.tutor_backend.model.dto.request.ClassesRequest;
import org.taitai.tutor_backend.model.dto.response.ApplyTutorResponse;
import org.taitai.tutor_backend.model.dto.response.ClassesResponse;

import java.util.List;


public interface ClassesService {
    ClassesResponse hiringsTutor(ClassesRequest classesRequest);

    List<Classes> getClasses();

    ApplyTutorResponse applyClass(Long classId);
}
