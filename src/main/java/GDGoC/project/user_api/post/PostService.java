package GDGoC.project.user_api.post;

import GDGoC.project.user_api.exception.DataNotFoundException;
import GDGoC.project.user_api.member.Member;
//exception(예외클래스)가 빨간색이 되어 있어 post 패키지 안에 exception이라는 패키지를 생성해서 정의시킴
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import GDGoC.project.user_api.member.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import GDGoC.project.user_api.post.LikeRepository;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getList(){
        return postRepository.findAll();
    }

    //단일 게시글 조회
    public Post getPost(Long id) {
        Optional<Post> question = this.postRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("post not found");
        }
    }

    public void createPost(String content, Member member) {
        Post post = new Post();
        post.setContent(content);
        post.setCreateDate(LocalDateTime.now());
        post.setAuthor(member);
        this.postRepository.save(post);
    }

    public void delete(Post post) {
        this.postRepository.delete(post);
    }
}
