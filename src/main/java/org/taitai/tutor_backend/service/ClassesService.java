package org.taitai.tutor_backend.service;

import org.taitai.tutor_backend.model.Classes;
import org.taitai.tutor_backend.request.ClassesRequest;
import org.taitai.tutor_backend.respone.ApplyTutorRespone;
import org.taitai.tutor_backend.respone.ClassesRespone;

import java.util.List;


public interface ClassesService {
    ClassesRespone hiringsTutor(ClassesRequest classesRequest);

    List<Classes> getClasses();

    ApplyTutorRespone applyClass(Long classId);
}
