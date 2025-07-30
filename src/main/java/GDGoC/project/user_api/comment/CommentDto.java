package GDGoC.project.user_api.comment;

import GDGoC.project.user_api.user.User;

import java.time.LocalDateTime;

public record CommentDto(
        Integer id,
        String content,
        String authorId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        int likeCount,
        boolean likedByMe        // 선택: 로그인 사용자가 좋아요 눌렀는지
) {
    public static CommentDto from(Comment comment, User me) {
        boolean liked = me != null && comment.getLikes().contains(me);
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor().getUserId(),
                comment.getCreateDate(),
                comment.getModifyDate(),
                comment.getLikes() == null ? 0 : comment.getLikes().size(),
                liked
        );
    }
}
