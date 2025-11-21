package org.taitai.tutor_backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taitai.tutor_backend.request.UpdateProfileUserRequest;
import org.taitai.tutor_backend.response.UserResponse;
import org.taitai.tutor_backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @GetMapping
    @RequestMapping("/me")
    public ResponseEntity<UserResponse> profile() {
        return userService.proFile();
    }
    @PatchMapping("/me/update")
    public UserResponse updateProfile(@RequestBody UpdateProfileUserRequest request) {
        return userService.updateProfile(request);
    }
}
