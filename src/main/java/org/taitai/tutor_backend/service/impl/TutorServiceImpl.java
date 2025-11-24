package org.taitai.tutor_backend.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.taitai.tutor_backend.model.dto.request.TutorSignUpRequest;
import org.taitai.tutor_backend.model.dto.request.UpdateProfileTutorRequest;
import org.taitai.tutor_backend.model.dto.response.TutorAssignmentResponse;
import org.taitai.tutor_backend.model.dto.response.TutorProfileResponse;
import org.taitai.tutor_backend.model.entity.Tutor;
import org.taitai.tutor_backend.model.entity.TutorApply;
import org.taitai.tutor_backend.model.entity.User;
import org.taitai.tutor_backend.repository.TutorApplyRepo;
import org.taitai.tutor_backend.repository.TutorRepo;
import org.taitai.tutor_backend.repository.UserRepo;
import org.taitai.tutor_backend.service.TutorService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TutorServiceImpl implements TutorService {
    private final TutorRepo tutorRepo;
    private final UserRepo userRepo;
    private final TutorApplyRepo tutorApplyRepo;

    @Override
    public ResponseEntity<String> signUp(TutorSignUpRequest tutorRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if (user.getTutor() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("message", "Bạn đã đăng ký làm gia sư rồi!").toString());
        }
        if (tutorRepo.findTutorByEmail(tutorRequest.getEmail()).isPresent())
            throw new RuntimeException("Email already exists");
        Tutor tutor = new Tutor();
        tutor.setUser(user);
        tutor.setEmail(tutorRequest.getEmail());
        tutor.setSubject(tutorRequest.getSubject());
        tutorRepo.save(tutor);
        return ResponseEntity.ok().body(tutor.getEmail());
    }

    @Override
    public List<TutorAssignmentResponse> getAssignedClasses() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if (user.getTutor() == null) {
            throw new RuntimeException("Tutor not found");
        }
        // vì tutor dùng id của user nên lấy id của user luôn
        log.info("Id user :{}" , user.getId());
        Tutor tutor = user.getTutor();
        List<TutorApply> tutorApply = tutorApplyRepo.findByTutor(tutor);
        if (tutorApply.isEmpty()){
            return Collections.emptyList();
        }
        return tutorApply.stream()
                          .map(apply -> TutorAssignmentResponse
                                  .builder()
                                  .username(apply.getClasses().getUsername())
                                  .description(apply.getClasses().getDescription())
                                  .build())
                           .toList();
    }

    @Override
    public TutorProfileResponse getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if (user.getTutor() == null) {
            throw new RuntimeException("Tutor not found");
        }
        Tutor tutor = user.getTutor();
        return TutorProfileResponse.builder()
                                   .email(tutor.getEmail())
                                   .subject(tutor.getSubject())
                                   .build();
    }

    @Override
    public TutorProfileResponse updateProfile(UpdateProfileTutorRequest updateProfileTutorRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if (user.getTutor() == null) {
            throw new RuntimeException("Tutor not found");
        }
        Tutor tutor = user.getTutor();
        if(tutorRepo.existsByEmail(updateProfileTutorRequest.getEmail()))
            throw new RuntimeException("Email exist!!!!!");
        if (updateProfileTutorRequest.getEmail() != null) {
            tutor.setEmail(updateProfileTutorRequest.getEmail());
        }
        if (updateProfileTutorRequest.getSubject() != null) {
            tutor.setSubject(updateProfileTutorRequest.getSubject());
        }
        tutorRepo.save(tutor);
        return TutorProfileResponse.builder()
                                   .email(tutor.getEmail())
                                   .subject(tutor.getSubject())
                                   .build();
    }
}
