package GDGoC.project.user_api.comment;

import jakarta.validation.constraints.NotEmpty;

public record CommentCreateRequest(
        @NotEmpty(message = "내용은 필수항목입니다.")
        String content
) {}
