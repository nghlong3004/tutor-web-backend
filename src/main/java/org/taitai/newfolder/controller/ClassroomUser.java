package org.taitai.newfolder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taitai.newfolder.model.dto.request.ClassesRequest;
import org.taitai.newfolder.service.ClassesService;

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
