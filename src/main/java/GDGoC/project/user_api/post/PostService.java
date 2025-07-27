package GDGoC.project.user_api.post;

import GDGoC.project.user_api.exception.DataNotFoundException;
import GDGoC.project.user_api.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getList(){
        return postRepository.findAll();
    }

    public Post getPost(Long id){
        Optional<Post> question = this.postRepository.findById(id);
        if(question.isPresent()){
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
