package GDGoC.project.user_api.post;

import GDGoC.project.user_api.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
