package org.taitai.tutor_backend.service;

import org.springframework.http.ResponseEntity;
import org.taitai.tutor_backend.request.UpdateProfileUserRequest;
import org.taitai.tutor_backend.respone.UserRespone;


public interface UserService {
    ResponseEntity<UserRespone> proFile();
    UserRespone updateProfile(UpdateProfileUserRequest updateProfileUserRequest);
}
