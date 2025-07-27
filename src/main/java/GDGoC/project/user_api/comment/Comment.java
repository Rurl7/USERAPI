package GDGoC.project.user_api.comment;

import GDGoC.project.user_api.member.Member;
import GDGoC.project.user_api.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY) /* 답변(many), 질문(one) N:1 관계를 나타냄 -> 외래키 관계가 생성됨 */
    private Post post; /* 댓글 엔티티에서 게시글 엔티티 참조 */

    @ManyToOne
    private Member author;

    private LocalDateTime modifyDate;
}

