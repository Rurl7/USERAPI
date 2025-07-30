package GDGoC.project.user_api.post;

import jakarta.validation.constraints.NotEmpty;

public record PostCreateRequest(
        @NotEmpty(message = "내용은 필수항목입니다.")
        String content
) {}
