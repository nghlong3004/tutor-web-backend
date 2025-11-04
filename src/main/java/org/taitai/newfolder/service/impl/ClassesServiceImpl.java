package org.taitai.newfolder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.taitai.newfolder.model.dto.request.ClassesRequest;
import org.taitai.newfolder.model.entity.Classes;
import org.taitai.newfolder.model.entity.User;
import org.taitai.newfolder.repository.ClassesRepo;
import org.taitai.newfolder.repository.UserRepo;
import org.taitai.newfolder.service.ClassesService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl implements ClassesService {
    private final ClassesRepo classesRepo;
    private final UserRepo userRepo;
    @Override
    public ResponseEntity<String> hiringsTutor(ClassesRequest classesRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username not found"));
        Classes classes = new Classes();
        classes.setUsername(classesRequest.getUsername());
        classes.setDescription(classesRequest.getDescription());
        classes.setUser(user);
        classesRepo.save(classes);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("message", "Bạn đã đăng ký tạo lớp thành công rồi!").toString());
    }
}
