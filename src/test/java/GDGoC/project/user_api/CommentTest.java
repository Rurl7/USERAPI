package GDGoC.project.user_api;

import GDGoC.project.user_api.comment.Comment;
import GDGoC.project.user_api.comment.CommentRepository;
import GDGoC.project.user_api.post.Post;
import GDGoC.project.user_api.post.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommentTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("comment 생성")
    void createComment(){
        Optional<Post> post = this.postRepository.findById(303);
        assertTrue(post.isPresent());

        Post p = post.get();

        Comment comment = new Comment();
        comment.setContent("This is a comment");
        comment.setPost(p);
        comment.setCreateDate(LocalDateTime.now());
        // TODO: author 테스트
        comment.setAuthor(null);
        this.commentRepository.save(comment);
    }

    @Test
    @DisplayName("아이디로 comment 조회")
    void getCommentById(){
        Optional<Comment> comment = this.commentRepository.findById(1);
        assertTrue(comment.isPresent());

        Comment c = comment.get();
        assertEquals(303, c.getPost().getId());
    }

    @Test
    @DisplayName("post 조회 후 comment list 조회")
    @Transactional // Test 코드를 수행할 때 메서드가 종료될 때까지 DB 세션을 유지하기 위한 애너테이션
    void getCommentList(){
        Optional<Post> post = this.postRepository.findById(303);
        assertTrue(post.isPresent());
        Post p = post.get();

        List<Comment> commentList = p.getCommentList();

        assertEquals(1, commentList.size());
        assertEquals("This is a comment", commentList.get(0).getContent());
    }
}
