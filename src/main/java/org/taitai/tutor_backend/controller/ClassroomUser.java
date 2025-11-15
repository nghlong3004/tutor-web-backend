package org.taitai.tutor_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taitai.tutor_backend.request.ClassesRequest;
import org.taitai.tutor_backend.service.ClassesService;
;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassroomUser {
    private final ClassesService classesService;

    @PostMapping
    @RequestMapping("/hiringstutor")
    public ResponseEntity<String> hiringstutor(@RequestBody ClassesRequest classesRequest) {
        return classesService.hiringsTutor(classesRequest) ;
    }

}
