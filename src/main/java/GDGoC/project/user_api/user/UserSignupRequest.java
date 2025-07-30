package GDGoC.project.user_api.user;

import jakarta.validation.constraints.NotEmpty;

public record UserSignupRequest(
        @NotEmpty String userId,
        @NotEmpty String password,
        @NotEmpty String username,
        @NotEmpty String phone
) {}
