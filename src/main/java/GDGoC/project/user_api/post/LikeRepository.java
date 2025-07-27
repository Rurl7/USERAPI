package GDGoC.project.user_api.post;

import GDGoC.project.user_api.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPostAndMember(Post post, Member member);
}
