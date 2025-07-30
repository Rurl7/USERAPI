package GDGoC.project.user_api.post;

import java.time.LocalDateTime;

/**
 * record 를 사용하면 “DTO에 필요한 값만 담고, 변경은 못 하게” 만드는 패턴을 한 줄로 표현할 수 있다.
 * 위 선언만으로 필드·생성자·Getter 가 모두 준비됨
 */

public record PostDto(
        Integer id,
        String content,
        String authorId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        int commentCount,
        int likeCount
) {
    /**
     * 데이터베이스 엔티티(Post)를 API용 DTO(PostDto) 로 안전하게 복사
     */
    public static PostDto from(Post post) {
        return new PostDto(
                post.getId(),
                post.getContent(),
                post.getAuthor().getUserId(),
                post.getCreateDate(),
                post.getModifyDate(),
                post.getCommentList() == null ? 0 : post.getCommentList().size(),
                post.getLikes() == null ? 0 : post.getLikes().size()
        );
    }
}
