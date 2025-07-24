package GDGoC.project.user_api.post;

import GDGoC.project.user_api.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService  postService;
    private final MemberService memberService;
}
