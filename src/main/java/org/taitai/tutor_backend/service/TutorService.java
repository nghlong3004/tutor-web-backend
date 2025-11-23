package org.taitai.tutor_backend.service;

import org.springframework.http.ResponseEntity;
import org.taitai.tutor_backend.model.dto.request.TutorSignUpRequest;
import org.taitai.tutor_backend.model.dto.request.UpdateProfileTutorRequest;
import org.taitai.tutor_backend.model.dto.response.TutorAssignmentResponse;
import org.taitai.tutor_backend.model.dto.response.TutorProfileResponse;

import java.util.List;


public interface TutorService {
    ResponseEntity<String> signUp(TutorSignUpRequest tutorRequest);
    List<TutorAssignmentResponse> getAssignedClasses();
    TutorProfileResponse getProfile();
    TutorProfileResponse updateProfile(UpdateProfileTutorRequest updateProfileTutorRequest);
}
