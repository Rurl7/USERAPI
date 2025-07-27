package GDGoC.project.user_api.post;

import GDGoC.project.user_api.member.Member;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post_like")

public class Like {
    @Id //어너테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* @ManyToOne: 다대일 관계 정의하는데 사용
    이 어노테이션은 테이블의 연관된 엔티티 조회에 사용 */
    @ManyToOne
    private Post post;

    @ManyToOne
    private Member member;

    private LocalDateTime creatDate;

}
