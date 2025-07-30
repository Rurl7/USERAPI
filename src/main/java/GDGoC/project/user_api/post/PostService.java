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

    public Post createPost(String content, User author) {
        Post post = new Post();
        post.setContent(content);
        post.setAuthor(author);
        post.setCreateDate(LocalDateTime.now());
        return this.postRepository.save(post);
    }

    public void modifyPost(Post post, String newContent) {
        post.setContent(newContent);
        post.setModifyDate(LocalDateTime.now());
        this.postRepository.save(post);
    }

    public void deletePost(Post post) {
        this.postRepository.delete(post);
    }

    public boolean toggleLike(Post post, User user) {
        if (post.getLikes().contains(user)) {
            post.getLikes().remove(user);
            return false;   // 좋아요 취소됨
        } else {
            post.getLikes().add(user);
            return true;    // 좋아요 추가됨
        }
    }
}