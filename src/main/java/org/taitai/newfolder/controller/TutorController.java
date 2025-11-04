package org.taitai.newfolder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taitai.newfolder.model.dto.request.TutorSignUpRequest;
import org.taitai.newfolder.service.TutorService;

@RestController
@RequestMapping("/tutor")
@RequiredArgsConstructor
public class TutorController {
    private final TutorService tutorService;
    @PostMapping
    @RequestMapping("/apply")
    public ResponseEntity<String> signup(@RequestBody TutorSignUpRequest tutorSignUpRequest) {
        return tutorService.signUp(tutorSignUpRequest);
    }
}
