package GDGoC.project.user_api.comment;

import GDGoC.project.user_api.post.Post;
import GDGoC.project.user_api.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    /*
    * 이렇게 데이터를 필요한 시점에 가져오는 방식을 지연(Lazy) 방식이라고 한다.
    * 이와 반대로 q 객체를 조회할 때 미리 answer 리스트를 모두 가져오는 방식은 즉시(Eager) 방식이라고 한다.
    * @OneToMany, @ManyToOne 애너테이션의 옵션으로 fetch=FetchType.LAZY 또는 fetch=FetchType.EAGER처럼 가져오는 방식을 설정할 수 있다.
    * */
    @ManyToOne(fetch = FetchType.LAZY) /* Comment(many), Post(one) N:1 관계를 나타냄 -> 외래키 관계가 생성됨 */
    private Post post; /* 댓글 엔티티에서 게시글 엔티티 참조 */

    @ManyToOne
    private User author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<User> likes;
}

