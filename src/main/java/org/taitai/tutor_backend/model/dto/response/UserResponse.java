package org.taitai.tutor_backend.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class UserResponse {
    String username;

    //todo : sau update thêm các trường thì thêm field vào đây
}
