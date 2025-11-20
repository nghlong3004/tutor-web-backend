package org.taitai.tutor_backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.taitai.tutor_backend.model.Classes;
import org.taitai.tutor_backend.model.Tutor;
import org.taitai.tutor_backend.model.TutorApply;
import org.taitai.tutor_backend.model.User;
import org.taitai.tutor_backend.repository.ClassesRepo;
import org.taitai.tutor_backend.repository.TutorApplyRepo;
import org.taitai.tutor_backend.repository.TutorRepo;
import org.taitai.tutor_backend.repository.UserRepo;
import org.taitai.tutor_backend.request.ClassesRequest;
import org.taitai.tutor_backend.respone.ApplyTutorRespone;
import org.taitai.tutor_backend.respone.ClassesRespone;
import org.taitai.tutor_backend.service.ClassesService;
import org.taitai.tutor_backend.type.ApplyStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl implements ClassesService {
    private final ClassesRepo classesRepo;
    private final UserRepo userRepo;
    private final TutorApplyRepo tutorApplyRepo;
    private final TutorRepo tutorRepo;

    @Override
    public ClassesRespone hiringsTutor(ClassesRequest classesRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username not found"));
        Classes classes = new Classes();
        classes.setUsername(classesRequest.getUsername());
        classes.setDescription(classesRequest.getDescription());
        classes.setUser(user);
        classes.setStatus(String.valueOf(ApplyStatus.OPEN));
        classesRepo.save(classes);
        return ClassesRespone.builder()
                             .username(username)
                             .description(classesRequest.getDescription())
                             .build();
    }

    @Override
    public List<Classes> getClasses() {
        return classesRepo.findByStatus(String.valueOf(ApplyStatus.OPEN));
    }

    @Override
    public ApplyTutorRespone applyClass(Long classId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Classes classes = classesRepo.findById(classId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found"));
        User user = userRepo.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username not found"));
        Tutor tutor = tutorRepo.findByUser(user).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor not found"));
        TutorApply tutorApply = new TutorApply();
        tutorApply.setClasses(classes);
        tutorApply.setTutor(tutor);
        tutorApply.setStatus(ApplyStatus.PENDING);
        tutorApplyRepo.save(tutorApply);
        return ApplyTutorRespone.builder()
                                .tutorId(tutor.getId())
                                .classId(classes.getId())
                                .status(ApplyStatus.PENDING)
                                .build();
    }

}
