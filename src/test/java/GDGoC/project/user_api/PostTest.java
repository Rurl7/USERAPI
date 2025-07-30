package GDGoC.project.user_api;

import GDGoC.project.user_api.post.Post;
import GDGoC.project.user_api.post.PostRepository;
import GDGoC.project.user_api.post.PostService;
import GDGoC.project.user_api.user.User;
import GDGoC.project.user_api.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostTest {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Post 생성")
    void createPost() {
        Post p1 = new Post();
        p1.setContent("sns");
        p1.setCreateDate(LocalDateTime.now());
        p1.setAuthor(userRepository.findById(1).get());
        this.postRepository.save(p1);
    }

    @Test
    @DisplayName("모든 post 조회")
    void findPostAll(){
        List<Post> all = this.postRepository.findAll();
        assertEquals(1, all.size());

        Post p = all.get(0);
        assertEquals("sns", p.getContent());
    }

    @Test
    @DisplayName("아이디로 post 조회")
    void findPostById(){
        Optional<Post> post = this.postRepository.findById(302);
        if(post.isPresent()){
            Post p = post.get();
            assertEquals("sns", p.getContent());
        }
    }

    @Test
    @DisplayName("내용으로 post 조회")
    void findPostByContent(){
        Post p = this.postRepository.findByContent("수정된 내용");
        assertEquals(302, p.getId());
    }

    @Test
    @DisplayName("post 내용 수정")
    void modifyPost(){
        Optional<Post> post = this.postRepository.findById(302);
        assertTrue(post.isPresent());
        Post p = post.get();
        p.setContent("수정된 내용");
        this.postRepository.save(p);
    }

    @Test
    @DisplayName("post 삭제")
    void deletePost(){
        assertEquals(1, this.postRepository.count());
        Optional<Post> post = this.postRepository.findById(302);
        assertTrue(post.isPresent());
        Post p = post.get();
        this.postRepository.delete(p);
        assertEquals(0, this.postRepository.count());
    }
}
