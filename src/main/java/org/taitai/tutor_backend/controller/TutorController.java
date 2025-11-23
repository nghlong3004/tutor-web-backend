package org.taitai.tutor_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taitai.tutor_backend.model.dto.request.TutorSignUpRequest;
import org.taitai.tutor_backend.model.dto.request.UpdateProfileTutorRequest;
import org.taitai.tutor_backend.model.dto.response.TutorAssignmentResponse;
import org.taitai.tutor_backend.model.dto.response.TutorProfileResponse;
import org.taitai.tutor_backend.service.TutorService;

import java.util.List;


@RestController
@RequestMapping("/tutor")
@RequiredArgsConstructor
public class TutorController {
    private final TutorService tutorService;
    @PostMapping("/apply")
    public ResponseEntity<String> signup(@RequestBody TutorSignUpRequest tutorSignUpRequest) {
        return tutorService.signUp(tutorSignUpRequest);
    }
    @GetMapping("/assignments")
    public List<TutorAssignmentResponse> getAssignments() {
        return tutorService.getAssignedClasses();
    }
    @GetMapping("/me")
    public TutorProfileResponse getMe() {
        return tutorService.getProfile();
    }
    @PostMapping("/updateProfile")
    public TutorProfileResponse updateProfile(@RequestBody UpdateProfileTutorRequest updateProfileTutorRequest) {
        return tutorService.updateProfile(updateProfileTutorRequest);
    }
}
