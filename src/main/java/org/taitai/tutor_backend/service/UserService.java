package org.taitai.tutor_backend.service;

import org.springframework.http.ResponseEntity;
import org.taitai.tutor_backend.model.dto.request.UpdateProfileUserRequest;
import org.taitai.tutor_backend.model.dto.response.UserResponse;


public interface UserService {
    ResponseEntity<UserResponse> proFile();
    UserResponse updateProfile(UpdateProfileUserRequest updateProfileUserRequest);
}
