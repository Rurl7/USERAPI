package GDGoC.project.user_api.post;

import GDGoC.project.user_api.exception.DataNotFoundException;
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
    private final LikeRepository likeRepository; // 좋아요 저장
    //모든 게시글 조회
    public List<Post> getList() {
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
    //게시글 생성 부분
    public void create(String content, Member author) {
        Post post = new Post();
        post.setContent(content);
        post.setCreateDate(LocalDateTime.now());
        post.setAuthor(author);
        postRepository.save(post);
    }

    //좋아요(like) 처리 부분
    public void like(Long postId, Member member) {
        Optional<Post> op = postRepository.findById(postId);
        if (op.isEmpty()) {
            throw new GDGoC.project.user_api.DataNotFoundException("게시물을 찾을 수 없습니다.");

        }
        //이미 좋아요를 눌렀다면 아무 동작 실행 없이 리턴되게 함
        Post post = op.get();
        if (likeRepository.existsByPostAndMember(post, member)) {
            return;

        }

        Like like = new Like();
        like.setPost(post);
        like.setMember(member);
        like.setCreatDate(LocalDateTime.now());
        likeRepository.save(like);
    }

}
