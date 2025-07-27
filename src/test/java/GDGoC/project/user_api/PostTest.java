package GDGoC.project.user_api;

import GDGoC.project.user_api.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostTest {
    @Autowired
    private PostService postService;

    @Test
    void createPost() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용";
            this.postService.createPost(content, null);
        }
    }
}
