package GDGoC.project.user_api.post;

//import GDGoC.project.user_api.comment.Comment;
//comment가 빨갛게 되는 오류 : comment 클래스를 생성하지 않았기 때문??
import GDGoC.project.user_api.member.Member;
import jakarta.persistence.*; //(JPA)
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    //@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    //private List<Comment> commentList;

    @ManyToOne
    private Member author;
}
