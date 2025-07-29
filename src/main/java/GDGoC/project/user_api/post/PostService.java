package GDGoC.project.user_api.post;

import GDGoC.project.user_api.exception.DataNotFoundException;
//exception(예외클래스)가 빨간색이 되어 있어 post 패키지 안에 exception이라는 패키지를 생성해서 정의시킴
import GDGoC.project.user_api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    //모든 게시글 조회
    public List<Post> getList() {
        return this.postRepository.findAll();
    }

    //단일 게시글 조회
    public Post getPost(Integer id) {
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new DataNotFoundException("post not found");
        }
    }

    // 게시글 생성 부분
    public void createPost(String content, User user) {
        Post post = new Post();
        post.setContent(content);
        post.setCreateDate(LocalDateTime.now());
        post.setAuthor(user);
        this.postRepository.save(post);
    }

    public void modifyPost(Post post, String content) {
        post.setContent(content);
        post.setModifyDate(LocalDateTime.now());
        this.postRepository.save(post);
    }

    public void deletePost(Post post) {
        this.postRepository.delete(post);
    }
}