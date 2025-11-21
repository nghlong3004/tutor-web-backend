package org.taitai.tutor_backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.taitai.tutor_backend.repository.UserRepo;
import org.taitai.tutor_backend.request.UpdateProfileUserRequest;
import org.taitai.tutor_backend.response.UserResponse;
import org.taitai.tutor_backend.service.UserService;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public ResponseEntity<UserResponse> proFile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        var user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        UserResponse userResponse = UserResponse.builder()
                                                .username(user.getUsername())
                                                .build();
        return ResponseEntity.ok(userResponse);
    }

    @Override
    public UserResponse updateProfile(UpdateProfileUserRequest updateProfileUserRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Authentication required");
        }
        String username = authentication.getName();
        var user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if(updateProfileUserRequest.getUsername() != null )
            user.setUsername(updateProfileUserRequest.getUsername());

        userRepo.save(user);

        return UserResponse.builder()
                           .username(user.getUsername())
                           .build();
    }
}
