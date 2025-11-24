package org.taitai.tutor_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.taitai.tutor_backend.model.dto.request.ClassesRequest;
import org.taitai.tutor_backend.model.dto.response.ApplyTutorResponse;
import org.taitai.tutor_backend.model.dto.response.ClassesResponse;
import org.taitai.tutor_backend.model.entity.Classes;
import org.taitai.tutor_backend.service.ClassesService;

import java.util.List;

@RestController
@RequestMapping("api/v1/class")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassesService classesService;

    @PostMapping
    @RequestMapping("/hiring")
    public ClassesResponse hiringstutor(@RequestBody ClassesRequest classesRequest) {
        return classesService.hiringsTutor(classesRequest);
    }

    @GetMapping
    public List<Classes> getClasses() {
        return classesService.getClasses();
    }

    @PostMapping("/{id}/apply")
    public ApplyTutorResponse applyClasses(@PathVariable Long id) {
        return classesService.applyClass(id);
    }

}
